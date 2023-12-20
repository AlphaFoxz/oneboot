package com.github.alphafoxz.oneboot.preset_sys.aspect;

import com.github.alphafoxz.oneboot.common.annotations.access_control.AbacResourceBizId;
import com.github.alphafoxz.oneboot.common.annotations.access_control.AccessControl;
import com.github.alphafoxz.oneboot.common.exceptions.OnebootApiDesignException;
import com.github.alphafoxz.oneboot.common.exceptions.OnebootAuthException;
import com.github.alphafoxz.oneboot.common.exceptions.OnebootDirtyDataException;
import com.github.alphafoxz.oneboot.common.exceptions.OnebootException;
import com.github.alphafoxz.oneboot.common.standard.access_control.AbacActionType;
import com.github.alphafoxz.oneboot.common.standard.access_control.AbacApi;
import com.github.alphafoxz.oneboot.common.standard.framework.ReliableService;
import com.github.alphafoxz.oneboot.common.toolkit.coding.ArrayUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.CollUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.ReflectUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.StrUtil;
import jakarta.annotation.Resource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.jooq.TableRecord;
import org.jooq.impl.TableImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * 访问控制切面
 */
@Aspect
@Component
public class AccessControlAspect {
    @Resource
    private AbacApi abacApi;

    @Pointcut("@annotation(com.github.alphafoxz.oneboot.common.annotations.access_control.AccessControl)")
    public void accessControlPointcut() {
    }

    @Around("accessControlPointcut() && @annotation(acAnno)")
    public Object aroundAccessControl(ProceedingJoinPoint point, AccessControl acAnno) {
        ReliableService service;
        if (point.getTarget() instanceof ReliableService s) {
            service = s;
        } else {
            String msg = StrUtil.format("{}类中的注解使用有误，AccessControl目前只能对可靠的服务实现", point.getTarget().getClass());
            throw new OnebootApiDesignException(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Set<Long> paramBizIdSet = CollUtil.newHashSet();
        Object[] args = point.getArgs();
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        AbacResourceBizId resultResourceBizIdAnno = method.getAnnotation(AbacResourceBizId.class);
        Annotation[][] paramAnnotations;
        Object result;
        boolean startTransaction = false;
        try {
            if (resultResourceBizIdAnno != null && AbacActionType.isUpdatableAction(acAnno.action())) {
                startTransaction = true;
                service.startTransaction();
            }
            // 获取入参时受保护的资源id
            if (ArrayUtil.isNotEmpty(args)) {
                paramAnnotations = method.getParameterAnnotations();
                for (int i = 0; i < paramAnnotations.length; i++) {
                    Annotation[] paramAnnotation = paramAnnotations[i];
                    if (ArrayUtil.isEmpty(paramAnnotation)) {
                        continue;
                    }
                    for (Annotation anno : paramAnnotation) {
                        if (anno instanceof AbacResourceBizId resourceBizIdAnno) {
                            putBizIdsFromArg(paramBizIdSet, resourceBizIdAnno, args[i]);
                        }
                    }
                }
            }
            // 验证传入的资源id
            TableImpl<?> table = ReflectUtil.newInstance(acAnno.tableClass());
            String schemaName = table.getSchema().getName();
            for (Long bizId : paramBizIdSet) {
                // FIXME 实现JWT认证之后修复此处subjectId功能
                if (!abacApi.access(1704372248082780160L, schemaName, table.getName(), bizId, acAnno.action(), acAnno.policies())) {
                    String msg = StrUtil.format("对指定资源的操作异常，没有权限，schemaName：{}, tableName：{}，bizId：{}", schemaName, table.getName(), bizId);
                    throw new OnebootAuthException(msg, HttpStatus.FORBIDDEN);
                }
            }
            // 执行方法
            result = point.proceed(args);
            // 检验资源
            if (resultResourceBizIdAnno != null) {
                for (Long resultBizId : putBizIdsFromArg(null, resultResourceBizIdAnno, result)) {
                    if (!paramBizIdSet.contains(resultBizId) && !abacApi.access(1704372248082780160L, schemaName, table.getName(), resultBizId, acAnno.action(), acAnno.policies())) {
                        String msg = StrUtil.format("对指定资源的操作异常，没有权限，schemaName：{}, tableName：{}，bizId：{}", schemaName, table.getName(), resultBizId);
                        throw new OnebootAuthException(msg, HttpStatus.FORBIDDEN);
                    }
                }
            }
            // 提交事务
            if (resultResourceBizIdAnno != null && AbacActionType.isUpdatableAction(acAnno.action())) {
                service.commitTransaction();
            }
        } catch (Throwable e) {
            // 回滚事务
            if (startTransaction) {
                try {
                    service.rollbackTransaction();
                } catch (Throwable t) {
                }
            }
            String msg = StrUtil.format("访问控制方法执行异常：{}", e.getMessage());
            if (e instanceof OnebootException onebootException) {
                throw onebootException;
            }
            throw new OnebootApiDesignException(msg, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
        return result;
    }

    /**
     * 从参数中获取资源bizId
     */
    private Set<Long> putBizIdsFromArg(@Nullable Set<Long> idSet, @NonNull AbacResourceBizId resourceBizIdAnno, @Nullable Object arg) {
        return putBizIdsFromArg(idSet, resourceBizIdAnno, arg, 0);
    }

    private Set<Long> putBizIdsFromArg(@Nullable Set<Long> idSet, @NonNull AbacResourceBizId resourceBizIdAnno, @Nullable Object arg, int deep) {
        if (arg == null) {
            throw new OnebootDirtyDataException("请检查是否存在脏数据，受保护的资源id为空", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (deep > 5) {
            throw new OnebootDirtyDataException("递归获取id时，深度大于5，请检查资源合法性", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (idSet == null) {
            idSet = CollUtil.newHashSet();
        }
        if (resourceBizIdAnno.fromContainer()) {
            if (arg instanceof Page<?> page) {
                for (Object obj : page.getContent()) {
                    putBizIdsFromArg(idSet, resourceBizIdAnno, obj, deep + 1);
                }
            } else if (arg instanceof Collection<?> collection) {
                for (Object obj : collection) {
                    putBizIdsFromArg(idSet, resourceBizIdAnno, obj, deep + 1);
                }
            } else if (arg instanceof Map<?, ?> map) {
                for (Object obj : map.values()) {
                    putBizIdsFromArg(idSet, resourceBizIdAnno, obj, deep + 1);
                }
            } else {
                throw new OnebootApiDesignException(StrUtil.format("不可解析的容器类型{}，请检查方法设计是否出错", arg.getClass().getName()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        if (arg instanceof Long l) {
            idSet.add((long) l);
        } else if (arg instanceof Record) {
            idSet.add((long) ReflectUtil.getFieldValue(arg, resourceBizIdAnno.fieldName()));
        } else if (arg instanceof TableRecord<?> tableRecord) {
            idSet.add((long) tableRecord.get(resourceBizIdAnno.fieldName()));
        } else if (arg instanceof Collection<?> collection) {
            for (Object obj : collection) {
                putBizIdsFromArg(idSet, resourceBizIdAnno, obj, deep + 1);
            }
        } else if (arg instanceof Page<?> page) {
            for (Object obj : page.getContent()) {
                putBizIdsFromArg(idSet, resourceBizIdAnno, obj, deep + 1);
            }
        } else {
            idSet.add((long) ReflectUtil.getFieldValue(arg, resourceBizIdAnno.fieldName()));
        }
        return idSet;
    }
}
