package com.github.alphafoxz.oneboot.core.standard.access_control;

public interface AbacResourceType {
    String getName();

    public static AbacResourceType of(String name) {
        return () -> name;
    }

    public default boolean eqauls(Object obj) {
        if (obj instanceof AbacResourceType resourceType) {
            return getName().equals(resourceType.getName());
        } else if (obj instanceof String value) {
            return getName().equals(value);
        }
        return false;
    }
}
