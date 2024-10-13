/*
 * This file is generated by jOOQ.
 */
package com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos;


import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.io.Serializable;
import java.time.OffsetDateTime;


/**
 * The table <code>preset_sys.psys_token</code>.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public record PsysToken(
    @Nullable Long id,
    @Nonnull String accessToken,
    @Nonnull String refreshToken,
    @Nonnull OffsetDateTime createTime,
    @Nonnull Long subjectId,
    @Nonnull OffsetDateTime expireTime
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
        final PsysToken other = (PsysToken) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.accessToken == null) {
            if (other.accessToken != null)
                return false;
        }
        else if (!this.accessToken.equals(other.accessToken))
            return false;
        if (this.refreshToken == null) {
            if (other.refreshToken != null)
                return false;
        }
        else if (!this.refreshToken.equals(other.refreshToken))
            return false;
        if (this.createTime == null) {
            if (other.createTime != null)
                return false;
        }
        else if (!this.createTime.equals(other.createTime))
            return false;
        if (this.subjectId == null) {
            if (other.subjectId != null)
                return false;
        }
        else if (!this.subjectId.equals(other.subjectId))
            return false;
        if (this.expireTime == null) {
            if (other.expireTime != null)
                return false;
        }
        else if (!this.expireTime.equals(other.expireTime))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.accessToken == null) ? 0 : this.accessToken.hashCode());
        result = prime * result + ((this.refreshToken == null) ? 0 : this.refreshToken.hashCode());
        result = prime * result + ((this.createTime == null) ? 0 : this.createTime.hashCode());
        result = prime * result + ((this.subjectId == null) ? 0 : this.subjectId.hashCode());
        result = prime * result + ((this.expireTime == null) ? 0 : this.expireTime.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PsysToken (");

        sb.append(id);
        sb.append(", ").append(accessToken);
        sb.append(", ").append(refreshToken);
        sb.append(", ").append(createTime);
        sb.append(", ").append(subjectId);
        sb.append(", ").append(expireTime);

        sb.append(")");
        return sb.toString();
    }
}
