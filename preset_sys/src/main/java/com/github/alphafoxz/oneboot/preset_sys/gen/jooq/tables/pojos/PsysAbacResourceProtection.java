/*
 * This file is generated by jOOQ.
 */
package com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos;


import java.io.Serializable;


/**
 * 访问控制_资源保护表
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PsysAbacResourceProtection implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Long id;
    private final String resourceType;
    private final String tableName;
    private final String schemaName;

    public PsysAbacResourceProtection(PsysAbacResourceProtection value) {
        this.id = value.id;
        this.resourceType = value.resourceType;
        this.tableName = value.tableName;
        this.schemaName = value.schemaName;
    }

    public PsysAbacResourceProtection(
        Long id,
        String resourceType,
        String tableName,
        String schemaName
    ) {
        this.id = id;
        this.resourceType = resourceType;
        this.tableName = tableName;
        this.schemaName = schemaName;
    }

    /**
     * Getter for <code>preset_sys.psys_abac_resource_protection.id</code>. 主键
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Getter for
     * <code>preset_sys.psys_abac_resource_protection.resource_type</code>. 资源类型
     * T表 R记录
     */
    public String getResourceType() {
        return this.resourceType;
    }

    /**
     * Getter for
     * <code>preset_sys.psys_abac_resource_protection.table_name</code>. 表名
     */
    public String getTableName() {
        return this.tableName;
    }

    /**
     * Getter for
     * <code>preset_sys.psys_abac_resource_protection.schema_name</code>. 结构名
     */
    public String getSchemaName() {
        return this.schemaName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final PsysAbacResourceProtection other = (PsysAbacResourceProtection) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.resourceType == null) {
            if (other.resourceType != null)
                return false;
        }
        else if (!this.resourceType.equals(other.resourceType))
            return false;
        if (this.tableName == null) {
            if (other.tableName != null)
                return false;
        }
        else if (!this.tableName.equals(other.tableName))
            return false;
        if (this.schemaName == null) {
            if (other.schemaName != null)
                return false;
        }
        else if (!this.schemaName.equals(other.schemaName))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.resourceType == null) ? 0 : this.resourceType.hashCode());
        result = prime * result + ((this.tableName == null) ? 0 : this.tableName.hashCode());
        result = prime * result + ((this.schemaName == null) ? 0 : this.schemaName.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PsysAbacResourceProtection (");

        sb.append(id);
        sb.append(", ").append(resourceType);
        sb.append(", ").append(tableName);
        sb.append(", ").append(schemaName);

        sb.append(")");
        return sb.toString();
    }
}
