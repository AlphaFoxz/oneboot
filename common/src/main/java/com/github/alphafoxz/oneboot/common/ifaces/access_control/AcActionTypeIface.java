package com.github.alphafoxz.oneboot.common.ifaces.access_control;

/**
 * 访问控制操作
 */
public interface AcActionTypeIface {
    public String getName();

    public static AcActionTypeIface of(String value) {
        return () -> value;
    }

    public default boolean eqauls(Object obj) {
        if (obj instanceof AcActionTypeIface actionType) {
            return getName().equals(actionType.getName());
        } else if (obj instanceof String value) {
            return getName().equals(value);
        }
        return false;
    }
}
