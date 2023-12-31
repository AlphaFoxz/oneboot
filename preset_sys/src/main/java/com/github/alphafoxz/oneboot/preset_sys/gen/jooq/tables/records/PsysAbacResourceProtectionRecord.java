/*
 * This file is generated by jOOQ.
 */
package com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records;


import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAbacResourceProtection;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAbacResourceProtectionPo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;
import org.springframework.lang.NonNull;


/**
 * 访问控制_资源保护表
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PsysAbacResourceProtectionRecord extends UpdatableRecordImpl<PsysAbacResourceProtectionRecord> implements Record5<Long, String, String, String, Boolean> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>preset_sys.psys_abac_resource_protection.id</code>. 主键
     */
    public PsysAbacResourceProtectionRecord setId(@NonNull Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_abac_resource_protection.id</code>. 主键
     */
    @NotNull
    @NonNull
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for
     * <code>preset_sys.psys_abac_resource_protection.resource_type</code>. 资源类型
     * 0表 1记录
     */
    public PsysAbacResourceProtectionRecord setResourceType(@NonNull String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for
     * <code>preset_sys.psys_abac_resource_protection.resource_type</code>. 资源类型
     * 0表 1记录
     */
    @NotNull
    @Size(max = 1)
    @NonNull
    public String getResourceType() {
        return (String) get(1);
    }

    /**
     * Setter for
     * <code>preset_sys.psys_abac_resource_protection.table_name</code>. 表名
     */
    public PsysAbacResourceProtectionRecord setTableName(@NonNull String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for
     * <code>preset_sys.psys_abac_resource_protection.table_name</code>. 表名
     */
    @NotNull
    @Size(max = 50)
    @NonNull
    public String getTableName() {
        return (String) get(2);
    }

    /**
     * Setter for
     * <code>preset_sys.psys_abac_resource_protection.schema_name</code>. 结构名
     */
    public PsysAbacResourceProtectionRecord setSchemaName(@NonNull String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for
     * <code>preset_sys.psys_abac_resource_protection.schema_name</code>. 结构名
     */
    @NotNull
    @Size(max = 20)
    @NonNull
    public String getSchemaName() {
        return (String) get(3);
    }

    /**
     * Setter for <code>preset_sys.psys_abac_resource_protection.enabled</code>.
     * 是否生效
     */
    public PsysAbacResourceProtectionRecord setEnabled(@NonNull Boolean value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_abac_resource_protection.enabled</code>.
     * 是否生效
     */
    @NotNull
    @NonNull
    public Boolean getEnabled() {
        return (Boolean) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    @NonNull
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    @NonNull
    public Row5<Long, String, String, String, Boolean> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    @NonNull
    public Row5<Long, String, String, String, Boolean> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    @NonNull
    public Field<Long> field1() {
        return PsysAbacResourceProtection.PSYS_ABAC_RESOURCE_PROTECTION.ID;
    }

    @Override
    @NonNull
    public Field<String> field2() {
        return PsysAbacResourceProtection.PSYS_ABAC_RESOURCE_PROTECTION.RESOURCE_TYPE;
    }

    @Override
    @NonNull
    public Field<String> field3() {
        return PsysAbacResourceProtection.PSYS_ABAC_RESOURCE_PROTECTION.TABLE_NAME;
    }

    @Override
    @NonNull
    public Field<String> field4() {
        return PsysAbacResourceProtection.PSYS_ABAC_RESOURCE_PROTECTION.SCHEMA_NAME;
    }

    @Override
    @NonNull
    public Field<Boolean> field5() {
        return PsysAbacResourceProtection.PSYS_ABAC_RESOURCE_PROTECTION.ENABLED;
    }

    @Override
    @NonNull
    public Long component1() {
        return getId();
    }

    @Override
    @NonNull
    public String component2() {
        return getResourceType();
    }

    @Override
    @NonNull
    public String component3() {
        return getTableName();
    }

    @Override
    @NonNull
    public String component4() {
        return getSchemaName();
    }

    @Override
    @NonNull
    public Boolean component5() {
        return getEnabled();
    }

    @Override
    @NonNull
    public Long value1() {
        return getId();
    }

    @Override
    @NonNull
    public String value2() {
        return getResourceType();
    }

    @Override
    @NonNull
    public String value3() {
        return getTableName();
    }

    @Override
    @NonNull
    public String value4() {
        return getSchemaName();
    }

    @Override
    @NonNull
    public Boolean value5() {
        return getEnabled();
    }

    @Override
    @NonNull
    public PsysAbacResourceProtectionRecord value1(@NonNull Long value) {
        setId(value);
        return this;
    }

    @Override
    @NonNull
    public PsysAbacResourceProtectionRecord value2(@NonNull String value) {
        setResourceType(value);
        return this;
    }

    @Override
    @NonNull
    public PsysAbacResourceProtectionRecord value3(@NonNull String value) {
        setTableName(value);
        return this;
    }

    @Override
    @NonNull
    public PsysAbacResourceProtectionRecord value4(@NonNull String value) {
        setSchemaName(value);
        return this;
    }

    @Override
    @NonNull
    public PsysAbacResourceProtectionRecord value5(@NonNull Boolean value) {
        setEnabled(value);
        return this;
    }

    @Override
    @NonNull
    public PsysAbacResourceProtectionRecord values(@NonNull Long value1, @NonNull String value2, @NonNull String value3, @NonNull String value4, @NonNull Boolean value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PsysAbacResourceProtectionRecord
     */
    public PsysAbacResourceProtectionRecord() {
        super(PsysAbacResourceProtection.PSYS_ABAC_RESOURCE_PROTECTION);
    }

    /**
     * Create a detached, initialised PsysAbacResourceProtectionRecord
     */
    public PsysAbacResourceProtectionRecord(@NonNull Long id, @NonNull String resourceType, @NonNull String tableName, @NonNull String schemaName, @NonNull Boolean enabled) {
        super(PsysAbacResourceProtection.PSYS_ABAC_RESOURCE_PROTECTION);

        setId(id);
        setResourceType(resourceType);
        setTableName(tableName);
        setSchemaName(schemaName);
        setEnabled(enabled);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised PsysAbacResourceProtectionRecord
     */
    public PsysAbacResourceProtectionRecord(PsysAbacResourceProtectionPo value) {
        super(PsysAbacResourceProtection.PSYS_ABAC_RESOURCE_PROTECTION);

        if (value != null) {
            setId(value.id());
            setResourceType(value.resourceType());
            setTableName(value.tableName());
            setSchemaName(value.schemaName());
            setEnabled(value.enabled());
            resetChangedOnNotNull();
        }
    }
}
