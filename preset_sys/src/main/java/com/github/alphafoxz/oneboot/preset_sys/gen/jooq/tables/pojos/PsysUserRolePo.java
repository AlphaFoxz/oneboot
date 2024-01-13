/*
 * This file is generated by jOOQ.
 */
package com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos;


import java.io.Serializable;
import java.time.OffsetDateTime;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;


/**
 * 角色表
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public record PsysUserRolePo(
    @NonNull Long id,
    @NonNull String roleName,
    @Nullable String description,
    @Nullable Boolean enabled,
    @Nullable Short status,
    @Nullable String roleCode,
    @NonNull OffsetDateTime createTime,
    @Nullable OffsetDateTime updateTime,
    @Nullable Integer remark
) implements Serializable {

    private static final long serialVersionUID = 1L;


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final PsysUserRolePo other = (PsysUserRolePo) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.roleName == null) {
            if (other.roleName != null)
                return false;
        }
        else if (!this.roleName.equals(other.roleName))
            return false;
        if (this.description == null) {
            if (other.description != null)
                return false;
        }
        else if (!this.description.equals(other.description))
            return false;
        if (this.enabled == null) {
            if (other.enabled != null)
                return false;
        }
        else if (!this.enabled.equals(other.enabled))
            return false;
        if (this.status == null) {
            if (other.status != null)
                return false;
        }
        else if (!this.status.equals(other.status))
            return false;
        if (this.roleCode == null) {
            if (other.roleCode != null)
                return false;
        }
        else if (!this.roleCode.equals(other.roleCode))
            return false;
        if (this.createTime == null) {
            if (other.createTime != null)
                return false;
        }
        else if (!this.createTime.equals(other.createTime))
            return false;
        if (this.updateTime == null) {
            if (other.updateTime != null)
                return false;
        }
        else if (!this.updateTime.equals(other.updateTime))
            return false;
        if (this.remark == null) {
            if (other.remark != null)
                return false;
        }
        else if (!this.remark.equals(other.remark))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.roleName == null) ? 0 : this.roleName.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.enabled == null) ? 0 : this.enabled.hashCode());
        result = prime * result + ((this.status == null) ? 0 : this.status.hashCode());
        result = prime * result + ((this.roleCode == null) ? 0 : this.roleCode.hashCode());
        result = prime * result + ((this.createTime == null) ? 0 : this.createTime.hashCode());
        result = prime * result + ((this.updateTime == null) ? 0 : this.updateTime.hashCode());
        result = prime * result + ((this.remark == null) ? 0 : this.remark.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PsysUserRolePo (");

        sb.append(id);
        sb.append(", ").append(roleName);
        sb.append(", ").append(description);
        sb.append(", ").append(enabled);
        sb.append(", ").append(status);
        sb.append(", ").append(roleCode);
        sb.append(", ").append(createTime);
        sb.append(", ").append(updateTime);
        sb.append(", ").append(remark);

        sb.append(")");
        return sb.toString();
    }
}
