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
public record PsysUserPo(
    @NonNull Long id,
    @NonNull String username,
    @NonNull String password,
    @NonNull String nickname,
    @NonNull Long subjectId,
    @NonNull Long accountId,
    @NonNull Boolean enabled,
    @NonNull Boolean expired,
    @Nullable String remark,
    @Nullable Long departmentId,
    @Nullable String phone,
    @NonNull OffsetDateTime createTime,
    @Nullable String avatar,
    @Nullable Short sex,
    @Nullable String email,
    @NonNull Short status
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
        final PsysUserPo other = (PsysUserPo) obj;
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
        if (this.remark == null) {
            if (other.remark != null)
                return false;
        }
        else if (!this.remark.equals(other.remark))
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
        if (this.createTime == null) {
            if (other.createTime != null)
                return false;
        }
        else if (!this.createTime.equals(other.createTime))
            return false;
        if (this.avatar == null) {
            if (other.avatar != null)
                return false;
        }
        else if (!this.avatar.equals(other.avatar))
            return false;
        if (this.sex == null) {
            if (other.sex != null)
                return false;
        }
        else if (!this.sex.equals(other.sex))
            return false;
        if (this.email == null) {
            if (other.email != null)
                return false;
        }
        else if (!this.email.equals(other.email))
            return false;
        if (this.status == null) {
            if (other.status != null)
                return false;
        }
        else if (!this.status.equals(other.status))
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
        result = prime * result + ((this.remark == null) ? 0 : this.remark.hashCode());
        result = prime * result + ((this.departmentId == null) ? 0 : this.departmentId.hashCode());
        result = prime * result + ((this.phone == null) ? 0 : this.phone.hashCode());
        result = prime * result + ((this.createTime == null) ? 0 : this.createTime.hashCode());
        result = prime * result + ((this.avatar == null) ? 0 : this.avatar.hashCode());
        result = prime * result + ((this.sex == null) ? 0 : this.sex.hashCode());
        result = prime * result + ((this.email == null) ? 0 : this.email.hashCode());
        result = prime * result + ((this.status == null) ? 0 : this.status.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PsysUserPo (");

        sb.append(id);
        sb.append(", ").append(username);
        sb.append(", ").append(password);
        sb.append(", ").append(nickname);
        sb.append(", ").append(subjectId);
        sb.append(", ").append(accountId);
        sb.append(", ").append(enabled);
        sb.append(", ").append(expired);
        sb.append(", ").append(remark);
        sb.append(", ").append(departmentId);
        sb.append(", ").append(phone);
        sb.append(", ").append(createTime);
        sb.append(", ").append(avatar);
        sb.append(", ").append(sex);
        sb.append(", ").append(email);
        sb.append(", ").append(status);

        sb.append(")");
        return sb.toString();
    }
}
