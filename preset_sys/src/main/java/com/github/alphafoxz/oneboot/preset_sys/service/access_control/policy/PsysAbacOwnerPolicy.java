package com.github.alphafoxz.oneboot.preset_sys.service.access_control.policy;

import com.github.alphafoxz.oneboot.common.interfaces.access_control.AbacAttr;
import com.github.alphafoxz.oneboot.common.interfaces.access_control.AbacPolicy;
import com.github.alphafoxz.oneboot.common.interfaces.access_control.impl.AbstractAbacOwnerPolicy;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 属性访问控制-拥有者策略
 * 只有资源的拥有者才能操作资源
 */
@Component
public class PsysAbacOwnerPolicy extends AbstractAbacOwnerPolicy {
    @Override
    public boolean access(long currentSubjectId, long ownerSubjectId, Map<String, AbacAttr> subjectAttrMap, Map<String, AbacAttr> resourceAttrMap, String actionType, Class<? extends AbacPolicy>[] currentPolicies) {
        return currentSubjectId == ownerSubjectId;
    }
}
