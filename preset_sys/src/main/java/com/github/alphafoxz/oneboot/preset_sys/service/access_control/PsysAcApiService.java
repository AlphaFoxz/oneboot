package com.github.alphafoxz.oneboot.preset_sys.service.access_control;

import cn.hutool.core.lang.Pair;
import com.github.alphafoxz.oneboot.common.enums.access_control.AcResourceTypeEnum;
import com.github.alphafoxz.oneboot.common.ifaces.access_control.AbacAttrIface;
import com.github.alphafoxz.oneboot.common.ifaces.access_control.AcActionTypeIface;
import com.github.alphafoxz.oneboot.common.ifaces.access_control.AcApiIface;
import com.github.alphafoxz.oneboot.common.toolkit.coding.StrUtil;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAbacResourceProtectionPo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
public class PsysAcApiService implements AcApiIface {
    @Resource
    private PsysAbacResourceService psysAbacResourceService;
    @Resource
    private PsysAbacResourceProtectionService psysAbacResourceProtectionService;
    @Resource
    private PsysDacApiService psysDacApiService;

    @Override
    public boolean access(@NonNull Long subjectId,
                          @NonNull String schemaName,
                          @NonNull String tableName,
                          @NonNull Long resourceId,
                          @NonNull AcActionTypeIface actionType) {
        //NOTE 第一步：查询资源是否受保护
        PsysAbacResourceProtectionPo psysAbacResourceProtection = psysAbacResourceProtectionService.queryProtectionInfo(schemaName, tableName);
        if (psysAbacResourceProtection == null) {
            //不受保护的资源
            return true;
        }
        String resourceType = psysAbacResourceProtection.resourceType();
        if (!AcResourceTypeEnum.TABLE.eqauls(resourceType) && !AcResourceTypeEnum.RECORD.eqauls(resourceType)) {
            String msg = StrUtil.format("未识别的资源类型：{}, schemaName = {}, tableName = {}", resourceType, schemaName, tableName);
            log.error(msg);
            throw new RuntimeException(msg);
        }
        //NOTE 第二步：DAC模块可以让当前主体扮演另一个主体
        Set<AbacAttrIface> subjectAttrs = null;
        Pair<Long, Set<AbacAttrIface>> dacResult = psysDacApiService.getSubjectIdAndAttrs(subjectId, resourceId, actionType);
        if (dacResult != null) {
            subjectId = dacResult.getKey();
            subjectAttrs = dacResult.getValue();
        }

        //XXX 实现策略判断
        return false;
    }

}
