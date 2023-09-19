package com.github.alphafoxz.oneboot.preset_sys.service.access_control;

import cn.hutool.core.lang.Pair;
import com.github.alphafoxz.oneboot.common.ifaces.access_control.AbacAttrIface;
import com.github.alphafoxz.oneboot.common.ifaces.access_control.AcActionTypeIface;
import com.github.alphafoxz.oneboot.common.ifaces.access_control.DacApiIface;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysDacAuthorization;
import jakarta.annotation.Resource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PsysDacApiService implements DacApiIface {
    @Resource
    private PsysDacAuthorizationService psysDacAuthorizationService;

    @Override
    @Nullable
    public Pair<Long, Set<AbacAttrIface>> getSubjectIdAndAttrs(Long subjectId, Long resourceId, AcActionTypeIface actionType) {

        //FIXME 获取扮演主体的属性
        return null;
    }
}
