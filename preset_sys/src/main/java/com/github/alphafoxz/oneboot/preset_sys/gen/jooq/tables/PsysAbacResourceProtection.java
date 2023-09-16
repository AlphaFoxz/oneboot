/*
 * This file is generated by jOOQ.
 */
package com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables;


import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.Keys;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.PresetSys;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysAbacResourceProtectionRecord;

import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function4;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row4;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * 访问控制_资源保护表
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PsysAbacResourceProtection extends TableImpl<PsysAbacResourceProtectionRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of
     * <code>preset_sys.psys_abac_resource_protection</code>
     */
    public static final PsysAbacResourceProtection PSYS_ABAC_RESOURCE_PROTECTION = new PsysAbacResourceProtection();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PsysAbacResourceProtectionRecord> getRecordType() {
        return PsysAbacResourceProtectionRecord.class;
    }

    /**
     * The column <code>preset_sys.psys_abac_resource_protection.id</code>. 主键
     */
    public final TableField<PsysAbacResourceProtectionRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false), this, "主键");

    /**
     * The column
     * <code>preset_sys.psys_abac_resource_protection.resource_type</code>. 资源类型
     * T表 R记录
     */
    public final TableField<PsysAbacResourceProtectionRecord, String> RESOURCE_TYPE = createField(DSL.name("resource_type"), SQLDataType.CHAR(1), this, "资源类型 T表 R记录");

    /**
     * The column
     * <code>preset_sys.psys_abac_resource_protection.table_name</code>. 表名
     */
    public final TableField<PsysAbacResourceProtectionRecord, String> TABLE_NAME = createField(DSL.name("table_name"), SQLDataType.VARCHAR(50).nullable(false), this, "表名");

    /**
     * The column
     * <code>preset_sys.psys_abac_resource_protection.schema_name</code>. 结构名
     */
    public final TableField<PsysAbacResourceProtectionRecord, String> SCHEMA_NAME = createField(DSL.name("schema_name"), SQLDataType.VARCHAR(20).nullable(false), this, "结构名");

    private PsysAbacResourceProtection(Name alias, Table<PsysAbacResourceProtectionRecord> aliased) {
        this(alias, aliased, null);
    }

    private PsysAbacResourceProtection(Name alias, Table<PsysAbacResourceProtectionRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("访问控制_资源保护表"), TableOptions.table());
    }

    /**
     * Create an aliased <code>preset_sys.psys_abac_resource_protection</code>
     * table reference
     */
    public PsysAbacResourceProtection(String alias) {
        this(DSL.name(alias), PSYS_ABAC_RESOURCE_PROTECTION);
    }

    /**
     * Create an aliased <code>preset_sys.psys_abac_resource_protection</code>
     * table reference
     */
    public PsysAbacResourceProtection(Name alias) {
        this(alias, PSYS_ABAC_RESOURCE_PROTECTION);
    }

    /**
     * Create a <code>preset_sys.psys_abac_resource_protection</code> table
     * reference
     */
    public PsysAbacResourceProtection() {
        this(DSL.name("psys_abac_resource_protection"), null);
    }

    public <O extends Record> PsysAbacResourceProtection(Table<O> child, ForeignKey<O, PsysAbacResourceProtectionRecord> key) {
        super(child, key, PSYS_ABAC_RESOURCE_PROTECTION);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : PresetSys.PRESET_SYS;
    }

    @Override
    public UniqueKey<PsysAbacResourceProtectionRecord> getPrimaryKey() {
        return Keys.PSYS_ABAC_RESOURCE_PROTECTION_PK;
    }

    @Override
    public PsysAbacResourceProtection as(String alias) {
        return new PsysAbacResourceProtection(DSL.name(alias), this);
    }

    @Override
    public PsysAbacResourceProtection as(Name alias) {
        return new PsysAbacResourceProtection(alias, this);
    }

    @Override
    public PsysAbacResourceProtection as(Table<?> alias) {
        return new PsysAbacResourceProtection(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public PsysAbacResourceProtection rename(String name) {
        return new PsysAbacResourceProtection(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public PsysAbacResourceProtection rename(Name name) {
        return new PsysAbacResourceProtection(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public PsysAbacResourceProtection rename(Table<?> name) {
        return new PsysAbacResourceProtection(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<Long, String, String, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function4<? super Long, ? super String, ? super String, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function4<? super Long, ? super String, ? super String, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
