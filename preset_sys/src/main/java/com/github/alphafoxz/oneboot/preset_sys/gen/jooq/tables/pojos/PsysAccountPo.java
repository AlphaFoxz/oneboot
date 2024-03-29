/*
 * This file is generated by jOOQ.
 */
package com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos;


import java.io.Serializable;

import org.springframework.lang.NonNull;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public record PsysAccountPo(
    @NonNull Long id,
    @NonNull Boolean expired,
    @NonNull Boolean enabled
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
        final PsysAccountPo other = (PsysAccountPo) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.expired == null) {
            if (other.expired != null)
                return false;
        }
        else if (!this.expired.equals(other.expired))
            return false;
        if (this.enabled == null) {
            if (other.enabled != null)
                return false;
        }
        else if (!this.enabled.equals(other.enabled))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.expired == null) ? 0 : this.expired.hashCode());
        result = prime * result + ((this.enabled == null) ? 0 : this.enabled.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PsysAccountPo (");

        sb.append(id);
        sb.append(", ").append(expired);
        sb.append(", ").append(enabled);

        sb.append(")");
        return sb.toString();
    }
}
