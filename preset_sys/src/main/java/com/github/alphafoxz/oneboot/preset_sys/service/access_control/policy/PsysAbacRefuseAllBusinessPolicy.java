package com.github.alphafoxz.oneboot.preset_sys.service.access_control.policy;

import com.github.alphafoxz.oneboot.common.interfaces.access_control.AbacAttr;
import com.github.alphafoxz.oneboot.common.interfaces.access_control.AbacPolicy;
import com.github.alphafoxz.oneboot.common.interfaces.access_control.impl.AbstractAbacBusinessPolicy;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 拒绝任何请求
 */
@Component
public class PsysAbacRefuseAllBusinessPolicy extends AbstractAbacBusinessPolicy {
    @Override
    public boolean access(Map<String, AbacAttr> subjectAttrMap, Map<String, AbacAttr> resourceAttrMap, String actionType, Class<? extends AbacPolicy>[] currentPolicies) {
        return false;
    }
}
