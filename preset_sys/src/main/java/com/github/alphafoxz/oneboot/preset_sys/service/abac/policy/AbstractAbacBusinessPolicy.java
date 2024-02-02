package com.github.alphafoxz.oneboot.preset_sys.service.abac.policy;

import com.github.alphafoxz.oneboot.core.standard.access_control.AbacAttr;
import com.github.alphafoxz.oneboot.core.standard.access_control.AbacPolicy;

import java.util.Map;

/**
 * 属性访问控制-业务类策略（安全管理员可以跳过此类策略）
 * 管理员可以跳过此类策略的原因：
 * 1、“用户需要寻求管理员的帮助，以解决各种系统设计外的特殊问题”这一需求是客观存在、而且难以避免的
 * 2、管理员难以达到用户的权限要求。即：管理员总是会用一个很大的权限去处理数据，而业务策略对正在帮助用户的管理员只有负面效果。
 *
 * @implSpec 所有的策略子类型都被设计成抽象类而不是接口，是为了防止后续实现策略时误用多继承而引发权限漏洞
 */
public abstract class AbstractAbacBusinessPolicy implements AbacPolicy {
    abstract public boolean access(Map<String, AbacAttr> subjectAttrMap, Map<String, AbacAttr> resourceAttrMap, String actionType, Class<? extends AbacPolicy>[] currentPolicies);
}
