package com.github.alphafoxz.oneboot.common.ifaces.access_control;

/**
 * 属性访问控制策略
 */
public interface AbacPolicyIface<T> {
    public boolean access(T t);
}
