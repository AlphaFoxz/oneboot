package com.github.alphafoxz.oneboot.preset_sys.service.access_control;

import cn.hutool.core.lang.Pair;
import com.github.alphafoxz.oneboot.common.ifaces.access_control.AbacAttrIface;
import com.github.alphafoxz.oneboot.common.ifaces.access_control.AcActionTypeIface;
import com.github.alphafoxz.oneboot.common.ifaces.access_control.DacApiIface;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PsysDacApiService implements DacApiIface {

    @Override
    @Nullable
    public <T extends AbacAttrIface> Pair<Long, Set<T>> getSubjectIdAndAttrs(Long subjectId, Long resourceId, AcActionTypeIface actionType) {
        //FIXME 获取扮演主体的属性
        return null;
    }
}
