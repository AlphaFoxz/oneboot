package com.github.alphafoxz.oneboot.core.spring;

import com.github.alphafoxz.oneboot.core.exceptions.OnebootConfigException;
import com.github.alphafoxz.oneboot.core.toolkit.coding.ArrayUtil;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.http.HttpStatus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 根据配置文件中的枚举判断是否注入
 */
public class OnPropertyEnumCondition<T extends Enum<T>> implements Condition {
    @Override
    @SneakyThrows
    @SuppressWarnings("unchecked")
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        MergedAnnotations annotations = metadata.getAnnotations();
        if (!annotations.isPresent(ConditionalOnPropertyEnum.class)) {
            return true;
        }
        MergedAnnotation<ConditionalOnPropertyEnum> annotation = annotations.get(ConditionalOnPropertyEnum.class);
        String value = annotation.getValue("name", String.class).orElse("");
        Class<T> enumClass = (Class<T>) annotation.getValue("enumClass", Class.class).orElse(null);
        String[] includeAllValue = annotation.getValue("includeAllValue", String[].class).orElse(null);
        String[] includeAnyValue = annotation.getValue("includeAnyValue", String[].class).orElse(null);
        String[] propertyValues = environment.getProperty(value, String[].class);
        if (ArrayUtil.isEmpty(propertyValues) || enumClass == null || (ArrayUtil.isEmpty(includeAnyValue) && ArrayUtil.isEmpty(includeAllValue))) {
            return false;
        }
        Method valuesMethod = null;
        try {
            valuesMethod = enumClass.getMethod("values", (Class<?>[]) null);
        } catch (NoSuchMethodException | SecurityException e) {
            throw new OnebootConfigException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
        T[] enums = null;
        try {
            enums = (T[]) valuesMethod.invoke(null, (Object[]) null);
        } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
            throw new OnebootConfigException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
        boolean flag = true;
        //传入的枚举值必须全匹配
        if (ArrayUtil.isNotEmpty(includeAllValue)) {
            checkValues(enums, includeAllValue);
            a:
            for (String enumStr : includeAllValue) {
                for (String propertyValue : propertyValues) {
                    if (enumStr.equalsIgnoreCase(propertyValue)) {
                        continue a;
                    }
                }
                flag = false;
                break;
            }
            if (!flag) {
                return false;
            }
        }
        //传入的枚举值匹配其中任意即可
        if (ArrayUtil.isNotEmpty(includeAnyValue)) {
            checkValues(enums, includeAnyValue);
            for (String enumStr : includeAnyValue) {
                for (String propertyValue : propertyValues) {
                    if (enumStr.equalsIgnoreCase(propertyValue)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 检查参数有效性
     */
    private void checkValues(T[] enums, String[] values) {
        a:
        for (String enumStr : values) {
            for (T anEnum : enums) {
                if (anEnum.name().equalsIgnoreCase(enumStr)) {
                    continue a;
                }
            }
            String eMsg = "OnPropertyEnumCondition:checkValues " + enumStr + "不是枚举的有效值";
            throw new OnebootConfigException(eMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
