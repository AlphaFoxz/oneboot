package com.github.alphafoxz.oneboot.core.standard.access_control;

/**
 * 属性访问控制策略
 *
 * @implSpec 所有的策略子类型都被设计成抽象类而不是接口，是为了防止后续实现策略时误用多继承而引发权限漏洞
 */
public interface AbacPolicy {
}
