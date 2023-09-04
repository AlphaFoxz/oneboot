/*
 * This file is generated by jOOQ.
 */
package com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos;


import org.jooq.JSONB;

import java.io.Serializable;


/**
 * 用户表
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PsysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String name;
    private final Long id;
    private final JSONB extInfo;

    public PsysUser(PsysUser value) {
        this.name = value.name;
        this.id = value.id;
        this.extInfo = value.extInfo;
    }

    public PsysUser(
        String name,
        Long id,
        JSONB extInfo
    ) {
        this.name = name;
        this.id = id;
        this.extInfo = extInfo;
    }

    /**
     * Getter for <code>preset_sys.psys_user.name</code>. 用户名
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for <code>preset_sys.psys_user.id</code>. 主键
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Getter for <code>preset_sys.psys_user.ext_info</code>. 其他信息
     */
    public JSONB getExtInfo() {
        return this.extInfo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final PsysUser other = (PsysUser) obj;
        if (this.name == null) {
            if (other.name != null)
                return false;
        }
        else if (!this.name.equals(other.name))
            return false;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.extInfo == null) {
            if (other.extInfo != null)
                return false;
        }
        else if (!this.extInfo.equals(other.extInfo))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.extInfo == null) ? 0 : this.extInfo.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PsysUser (");

        sb.append(name);
        sb.append(", ").append(id);
        sb.append(", ").append(extInfo);

        sb.append(")");
        return sb.toString();
    }
}
