/*
 * This file is generated by jOOQ.
 */
package com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos;


import java.io.Serializable;

import org.springframework.lang.NonNull;


/**
 * 属性访问控制_资源表
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public record PsysAbacResourcePo(
    @NonNull Long id,
    @NonNull Long ownerSubjectId,
    @NonNull String resourceAttrSet,
    @NonNull String actionTypeSet
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
        final PsysAbacResourcePo other = (PsysAbacResourcePo) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.ownerSubjectId == null) {
            if (other.ownerSubjectId != null)
                return false;
        }
        else if (!this.ownerSubjectId.equals(other.ownerSubjectId))
            return false;
        if (this.resourceAttrSet == null) {
            if (other.resourceAttrSet != null)
                return false;
        }
        else if (!this.resourceAttrSet.equals(other.resourceAttrSet))
            return false;
        if (this.actionTypeSet == null) {
            if (other.actionTypeSet != null)
                return false;
        }
        else if (!this.actionTypeSet.equals(other.actionTypeSet))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.ownerSubjectId == null) ? 0 : this.ownerSubjectId.hashCode());
        result = prime * result + ((this.resourceAttrSet == null) ? 0 : this.resourceAttrSet.hashCode());
        result = prime * result + ((this.actionTypeSet == null) ? 0 : this.actionTypeSet.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PsysAbacResourcePo (");

        sb.append(id);
        sb.append(", ").append(ownerSubjectId);
        sb.append(", ").append(resourceAttrSet);
        sb.append(", ").append(actionTypeSet);

        sb.append(")");
        return sb.toString();
    }
}
