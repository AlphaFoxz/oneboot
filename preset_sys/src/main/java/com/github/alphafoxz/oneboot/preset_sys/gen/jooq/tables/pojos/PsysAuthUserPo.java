/*
 * This file is generated by jOOQ.
 */
package com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos;


import java.io.Serializable;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;


/**
 * 用户表
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public record PsysAuthUserPo(
    @NonNull Long id,
    @NonNull String username,
    @NonNull String password,
    @NonNull String nickname,
    @NonNull Long subjectId,
    @NonNull Long accountId,
    @NonNull Boolean enabled,
    @NonNull Boolean expired,
    @Nullable String description,
    @Nullable Long departmentId,
    @Nullable String phone
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
        final PsysAuthUserPo other = (PsysAuthUserPo) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.username == null) {
            if (other.username != null)
                return false;
        }
        else if (!this.username.equals(other.username))
            return false;
        if (this.password == null) {
            if (other.password != null)
                return false;
        }
        else if (!this.password.equals(other.password))
            return false;
        if (this.nickname == null) {
            if (other.nickname != null)
                return false;
        }
        else if (!this.nickname.equals(other.nickname))
            return false;
        if (this.subjectId == null) {
            if (other.subjectId != null)
                return false;
        }
        else if (!this.subjectId.equals(other.subjectId))
            return false;
        if (this.accountId == null) {
            if (other.accountId != null)
                return false;
        }
        else if (!this.accountId.equals(other.accountId))
            return false;
        if (this.enabled == null) {
            if (other.enabled != null)
                return false;
        }
        else if (!this.enabled.equals(other.enabled))
            return false;
        if (this.expired == null) {
            if (other.expired != null)
                return false;
        }
        else if (!this.expired.equals(other.expired))
            return false;
        if (this.description == null) {
            if (other.description != null)
                return false;
        }
        else if (!this.description.equals(other.description))
            return false;
        if (this.departmentId == null) {
            if (other.departmentId != null)
                return false;
        }
        else if (!this.departmentId.equals(other.departmentId))
            return false;
        if (this.phone == null) {
            if (other.phone != null)
                return false;
        }
        else if (!this.phone.equals(other.phone))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.username == null) ? 0 : this.username.hashCode());
        result = prime * result + ((this.password == null) ? 0 : this.password.hashCode());
        result = prime * result + ((this.nickname == null) ? 0 : this.nickname.hashCode());
        result = prime * result + ((this.subjectId == null) ? 0 : this.subjectId.hashCode());
        result = prime * result + ((this.accountId == null) ? 0 : this.accountId.hashCode());
        result = prime * result + ((this.enabled == null) ? 0 : this.enabled.hashCode());
        result = prime * result + ((this.expired == null) ? 0 : this.expired.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.departmentId == null) ? 0 : this.departmentId.hashCode());
        result = prime * result + ((this.phone == null) ? 0 : this.phone.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PsysAuthUserPo (");

        sb.append(id);
        sb.append(", ").append(username);
        sb.append(", ").append(password);
        sb.append(", ").append(nickname);
        sb.append(", ").append(subjectId);
        sb.append(", ").append(accountId);
        sb.append(", ").append(enabled);
        sb.append(", ").append(expired);
        sb.append(", ").append(description);
        sb.append(", ").append(departmentId);
        sb.append(", ").append(phone);

        sb.append(")");
        return sb.toString();
    }
}
