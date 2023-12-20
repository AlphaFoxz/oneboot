package com.github.alphafoxz.oneboot.common.standard.access_control.impl;

import com.github.alphafoxz.oneboot.common.standard.access_control.AbacAttr;
import com.github.alphafoxz.oneboot.common.standard.access_control.AbacPolicy;

import java.util.Map;

/**
 * 属性访问控制-所有权类策略（安全管理员无法跳过）
 * 原因和对策：
 * 1、根据以往的开发经验，所有权类型的安全判断是最重要的策略
 * 2、管理员永远都不应该去操作敏感业务数据，而敏感数据几乎必然是受所有权策略保护的
 * 3、显然此类策略不利于管理员在特殊情况下对用户的帮助，所以设计了动态访问授权机制（对应Dac功能），
 * 如果安全管理员可以获得用户的授权，那么管理员操作敏感数据是可以被容忍的。
 * （实际上是管理员扮演了用户，通过了所有权类型的策略验证，同时利用自己的管理员身份跳过了繁琐的业务类型策略）
 *
 * @implSpec 所有的策略子类型都被设计成抽象类而不是接口，是为了防止后续实现策略时误用多继承而引发权限漏洞
 */
public abstract class AbstractAbacOwnerPolicy implements AbacPolicy {
    abstract public boolean access(long currentSubjectId, long ownerSubjectId, Map<String, AbacAttr> subjectAttrMap, Map<String, AbacAttr> resourceAttrMap, String actionType, Class<? extends AbacPolicy>[] currentPolicies);
}
