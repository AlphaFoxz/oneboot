package com.github.alphafoxz.oneboot.common.ifaces.access_control;

import cn.hutool.core.lang.Pair;
import com.github.alphafoxz.oneboot.common.toolkit.coding.StrUtil;

public interface AbacAttrIface {
    public String getName();

    public String getValue();

    public static AbacAttrIface of(String dbString) {
        //XXX 此处解析不应该是split
        String[] split = dbString.split("=");
        String name = split[0];
        String value = split[0];
        return new AbacAttrIface() {
            @Override
            public String getName() {
                return name;
            }

            @Override
            public String getValue() {
                return value;
            }
        };
    }

    public static AbacAttrIface of(Pair<String, String> attr) {
        String name = attr.getKey();
        String value = attr.getValue();
        return new AbacAttrIface() {
            @Override
            public String getName() {
                return name;
            }

            @Override
            public String getValue() {
                return value;
            }
        };
    }

    public default boolean eqauls(Object obj) {
        if (obj instanceof AbacAttrIface dacRoleType) {
            return getValue().equals(dacRoleType.getValue());
        } else if (obj instanceof String name) {
            return getName().equals(name) && StrUtil.isBlank(getName());
        } else if (obj instanceof Pair<?, ?> attr) {
            return StrUtil.equals(getName(), attr.getKey() == null ? null : attr.getKey().toString())
                    && StrUtil.equals(getValue(), attr.getValue() == null ? null : attr.getValue().toString());
        }
        return false;
    }

    public default String toDbString() {
        if (getValue() == null) {
            return getName();
        }
        return StrUtil.wrap(getName() + "=" + getValue(), "");
    }
}
