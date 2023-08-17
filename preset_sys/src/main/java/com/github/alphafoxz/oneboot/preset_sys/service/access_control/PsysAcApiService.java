package com.github.alphafoxz.oneboot.preset_sys.service.access_control;

import cn.hutool.json.JSONArray;
import com.github.alphafoxz.oneboot.common.exceptions.OnebootAuthException;
import com.github.alphafoxz.oneboot.common.interfaces.access_control.AbacAttr;
import com.github.alphafoxz.oneboot.common.interfaces.access_control.AbacPolicy;
import com.github.alphafoxz.oneboot.common.interfaces.access_control.AcApi;
import com.github.alphafoxz.oneboot.common.interfaces.access_control.impl.AbacAttrImpl;
import com.github.alphafoxz.oneboot.common.interfaces.access_control.impl.AbstractAbacBusinessPolicy;
import com.github.alphafoxz.oneboot.common.interfaces.access_control.impl.AbstractAbacOwnerPolicy;
import com.github.alphafoxz.oneboot.common.toolkit.Pair;
import com.github.alphafoxz.oneboot.common.toolkit.coding.JSONUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.MapUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.SpringUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.StrUtil;
import com.github.alphafoxz.oneboot.preset_sys.enums.access_control.AbacResourceTypeEnum;
import com.github.alphafoxz.oneboot.preset_sys.enums.access_control.AbacRoleAttrNameEnum;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAbacResourcePo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAbacResourceProtectionPo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAbacSubjectPo;
import com.github.alphafoxz.oneboot.preset_sys.service.access_control.crud.PsysAbacResourceCrudService;
import com.github.alphafoxz.oneboot.preset_sys.service.access_control.crud.PsysAbacResourceProtectionCrudService;
import com.github.alphafoxz.oneboot.preset_sys.service.access_control.crud.PsysAbacSubjectCrudService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.github.alphafoxz.oneboot.preset_sys.gen.jooq.Tables.PSYS_ABAC_RESOURCE;
import static com.github.alphafoxz.oneboot.preset_sys.gen.jooq.Tables.PSYS_ABAC_RESOURCE_PROTECTION;

/**
 * 访问控制api
 */
@Slf4j
@Service
public class PsysAcApiService implements AcApi {
    @Resource
    private PsysAbacResourceCrudService psysAbacResourceCrudService;
    @Resource
    private PsysAbacSubjectCrudService psysAbacSubjectCrudService;
    @Resource
    private PsysAbacResourceProtectionCrudService psysAbacResourceProtectionCrudService;
    @Resource
    private PsysDacApiService psysDacApiService;

    @Override
    public boolean access(@NonNull Long subjectId,
                          @NonNull String schemaName,
                          @NonNull String tableName,
                          @Nullable Long resourceBizId,
                          @NonNull String actionType,
                          @NonNull Class<? extends AbacPolicy>[] policiesClass) {
        //TODO 测试

        //NOTE 第一步：查询资源是否受保护
        List<PsysAbacResourceProtectionPo> psysAbacResourceProtectionPoList = psysAbacResourceProtectionCrudService.selectList(5,
                PSYS_ABAC_RESOURCE_PROTECTION.SCHEMA_NAME.eq(schemaName),
                PSYS_ABAC_RESOURCE_PROTECTION.TABLE_NAME.eq(tableName),
                PSYS_ABAC_RESOURCE_PROTECTION.ENABLED.eq(true)
        );
        if (psysAbacResourceProtectionPoList.isEmpty()) {
            //不受保护的资源
            return true;
        }
        if (psysAbacResourceProtectionPoList.size() > 2) {
            log.warn("同一资源有多于2种保护类型，请检查是否有重复数据或脏数据tableName：{}", tableName);
        }
        //这个for循环理论上最多循环2次，一次是判断表权限，一次是判断数据行权限
        for (PsysAbacResourceProtectionPo psysAbacResourceProtectionPo : psysAbacResourceProtectionPoList) {
            //保护类型
            String resourceType = psysAbacResourceProtectionPo.resourceType();
            if (!StrUtil.equalsAny(resourceType, AbacResourceTypeEnum.TABLE.getName(), AbacResourceTypeEnum.RECORD.getName())) {
                log.error("未识别的资源类型：{}, schemaName = {}, tableName = {}", resourceType, schemaName, tableName);
                return false;
            }
            //NOTE 第二步：查询资源信息
            PsysAbacResourcePo psysAbacResourcePo;
            if (AbacResourceTypeEnum.TABLE.eqauls(resourceType)) {
                psysAbacResourcePo = psysAbacResourceCrudService.selectOne(
                        PSYS_ABAC_RESOURCE.PROTECTION_ID.eq(psysAbacResourceProtectionPo.id()),
                        PSYS_ABAC_RESOURCE.BUSINESS_ID.isNull()
                );
            } else {
                psysAbacResourcePo = psysAbacResourceCrudService.selectOne(
                        PSYS_ABAC_RESOURCE.PROTECTION_ID.eq(psysAbacResourceProtectionPo.id()),
                        PSYS_ABAC_RESOURCE.BUSINESS_ID.eq(resourceBizId)
                );
            }
            if (psysAbacResourcePo == null) {
                String msg = StrUtil.format("未检测到resource，请检查数据完整性：resourceType = {}, schemaName = {}, tableName = {}, bizId = {}", resourceType, schemaName, tableName, resourceBizId);
                log.error(msg);
                throw new OnebootAuthException(msg, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            Map<String, AbacAttr> resourceAttrs = stringToAttrMap(psysAbacResourcePo.resourceAttrSet());
            //NOTE 第三步：DAC模块可以让当前主体扮演另一个主体
            Map<String, AbacAttr> subjectAttrs = getAttrMapBySubjectId(subjectId);
            Pair<Long, Map<String, AbacAttr>> dacResult = psysDacApiService.queryAuthorization(psysAbacResourcePo.ownerSubjectId(), psysAbacResourcePo.id(), subjectId, actionType);
            if (dacResult != null) {
                subjectId = dacResult.getKey();
                subjectAttrs.putAll(dacResult.getValue());
            }
            //NOTE 第四步：根据实际策略判断是否有权限
            //除了资源所属验证不可跳过，扮演管理员角色的主体可跳过其他所有策略
            boolean isSecurityAdmin = subjectAttrs.containsKey(AbacRoleAttrNameEnum.SECURITY_ADMIN.getName());
            for (Class<? extends AbacPolicy> policyClass : policiesClass) {
                AbacPolicy policy = SpringUtil.getBean(policyClass);
                if (policy instanceof AbstractAbacBusinessPolicy businessPolicy) {
                    if (isSecurityAdmin) {
                        continue;
                    }
                    if (!businessPolicy.access(subjectAttrs, resourceAttrs, actionType, policiesClass)) {
                        return false;
                    }
                } else if (policy instanceof AbstractAbacOwnerPolicy ownerPolicy && ownerPolicy.access(subjectId, psysAbacResourcePo.ownerSubjectId(), subjectAttrs, resourceAttrs, actionType, policiesClass)) {
                    return false;
                } else {
                    log.error("未知的策略类型：{}", policy.getClass().getName());
                    return false;
                }
            }
        }
        return true;
    }

    private Map<String, AbacAttr> stringToAttrMap(@NonNull String str) {
        JSONArray jsonArray = JSONUtil.parseArray(str);
        Map<String, AbacAttr> attrMap = MapUtil.newHashMap();
        for (Object o : jsonArray) {
            AbacAttr abacAttr = AbacAttrImpl.of(o.toString());
            attrMap.put(abacAttr.getName(), abacAttr);
        }
        return attrMap;
    }

    public Map<String, AbacAttr> getAttrMapByResourceId(long id) {
        PsysAbacResourcePo po = psysAbacResourceCrudService.selectOne(id);
        if (po == null) {
            String msg = StrUtil.format("查询不到资源信息，请检查数据完整性，resourceId：{}", id);
            log.error(msg);
            throw new OnebootAuthException(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return stringToAttrMap(po.resourceAttrSet());
    }

    public Map<String, AbacAttr> getAttrMapBySubjectId(long id) {
        PsysAbacSubjectPo po = psysAbacSubjectCrudService.selectOne(id);
        if (po == null) {
            String msg = StrUtil.format("查询不到资源信息，请检查数据完整性，subjectId：{}", id);
            log.error(msg);
            throw new OnebootAuthException(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return stringToAttrMap(po.attrSet());
    }
}
