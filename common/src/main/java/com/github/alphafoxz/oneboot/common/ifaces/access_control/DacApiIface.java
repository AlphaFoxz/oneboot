package com.github.alphafoxz.oneboot.common.ifaces.access_control;

import cn.hutool.core.lang.Pair;

import java.util.Set;

/**
 * 动态访问控制API
 */
public interface DacApiIface {
    public Pair<Long, Set<AbacAttrIface>> getSubjectIdAndAttrs(Long subjectId, Long resourceId, AcActionTypeIface actionType);
}
