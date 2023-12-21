package com.github.alphafoxz.oneboot.common.standard.access_control;

import com.github.alphafoxz.oneboot.common.toolkit.coding.JSONUtil;
import org.springframework.lang.Nullable;

public interface AbacAttr extends AbacAttrName {
    @Nullable
    public String getValue();

    public default boolean isSameName(AbacAttr abacAttr) {
        return getName().equals(abacAttr.getName());
    }

    public default String toDbString() {
        if (getValue() == null) {
            return getName();
        }
        return getName() + "=" + JSONUtil.quote(getValue(), false);
    }
}
