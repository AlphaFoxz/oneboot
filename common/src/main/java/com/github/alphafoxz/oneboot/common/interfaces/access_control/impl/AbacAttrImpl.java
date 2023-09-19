package com.github.alphafoxz.oneboot.common.interfaces.access_control.impl;

import com.github.alphafoxz.oneboot.common.interfaces.access_control.AbacAttr;
import com.github.alphafoxz.oneboot.common.interfaces.access_control.AbacAttrName;
import com.github.alphafoxz.oneboot.common.toolkit.Pair;
import com.github.alphafoxz.oneboot.common.toolkit.coding.JSONUtil;
import lombok.Getter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Getter
public class AbacAttrImpl implements AbacAttr {
    private final String name;
    private String value;

    private AbacAttrImpl(@NonNull AbacAttrName attrName, @Nullable String value) {
        this.name = attrName.getName();
    }

    private AbacAttrImpl(@NonNull String name, @Nullable String value) {
        this.name = name;
        this.value = value;
    }

    public AbacAttrImpl setValue(String value) {
        this.value = value;
        return this;
    }

    @NonNull
    public static AbacAttrImpl of(@NonNull AbacAttrName attrName, String value) {
        return new AbacAttrImpl(attrName, value);
    }

    @NonNull
    public static AbacAttrImpl of(@NonNull String name, String value) {
        return new AbacAttrImpl(name, value);
    }

    @NonNull
    public static AbacAttrImpl of(@NonNull String dbString) {
        String[] split = dbString.split("=");
        return new AbacAttrImpl(split[0], split.length > 1 ? JSONUtil.unquote(split[1], false) : null);
    }

    @NonNull
    public static AbacAttrImpl of(@NonNull Pair<String, String> attr) {
        return new AbacAttrImpl(attr.getKey(), attr.getValue());
    }

    public boolean isSameName(@NonNull String dbString) {
        return isSameName(of(dbString));
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object instanceof AbacAttr abacAttr) {
            if (getValue() == null) {
                return abacAttr.getValue() == null;
            }
            return getValue().equals(abacAttr.getValue());
        } else if (object instanceof AbacAttrName abacAttrName) {
            return getName().equals(abacAttrName.getName()) && getValue() == null;
        }
        return false;
    }
}
