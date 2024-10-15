/*
 * This file is generated by jOOQ.
 */
package com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos;


import java.io.Serializable;
import java.time.OffsetDateTime;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;


/**
 * 用户表
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public record PsysUser(
    @Nullable Long id,
    @NonNull String username,
    @NonNull String nickname,
    @NonNull String userStatus,
    @NonNull OffsetDateTime createTime,
    @NonNull OffsetDateTime _Version,
    @NonNull Long accountId
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
        final PsysUser other = (PsysUser) obj;
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
        if (this.nickname == null) {
            if (other.nickname != null)
                return false;
        }
        else if (!this.nickname.equals(other.nickname))
            return false;
        if (this.userStatus == null) {
            if (other.userStatus != null)
                return false;
        }
        else if (!this.userStatus.equals(other.userStatus))
            return false;
        if (this.createTime == null) {
            if (other.createTime != null)
                return false;
        }
        else if (!this.createTime.equals(other.createTime))
            return false;
        if (this._Version == null) {
            if (other._Version != null)
                return false;
        }
        else if (!this._Version.equals(other._Version))
            return false;
        if (this.accountId == null) {
            if (other.accountId != null)
                return false;
        }
        else if (!this.accountId.equals(other.accountId))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.username == null) ? 0 : this.username.hashCode());
        result = prime * result + ((this.nickname == null) ? 0 : this.nickname.hashCode());
        result = prime * result + ((this.userStatus == null) ? 0 : this.userStatus.hashCode());
        result = prime * result + ((this.createTime == null) ? 0 : this.createTime.hashCode());
        result = prime * result + ((this._Version == null) ? 0 : this._Version.hashCode());
        result = prime * result + ((this.accountId == null) ? 0 : this.accountId.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PsysUser (");

        sb.append(id);
        sb.append(", ").append(username);
        sb.append(", ").append(nickname);
        sb.append(", ").append(userStatus);
        sb.append(", ").append(createTime);
        sb.append(", ").append(_Version);
        sb.append(", ").append(accountId);

        sb.append(")");
        return sb.toString();
    }
}
