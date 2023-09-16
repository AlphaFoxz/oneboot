package com.github.alphafoxz.oneboot.common.ifaces.access_control;

public interface AcResourceTypeIface {
    String getName();

    public static AcResourceTypeIface of(String name) {
        return () -> name;
    }

    public default boolean eqauls(Object obj) {
        if (obj instanceof AcResourceTypeIface resourceType) {
            return getName().equals(resourceType.getName());
        } else if (obj instanceof String value) {
            return getName().equals(value);
        }
        return false;
    }
}
