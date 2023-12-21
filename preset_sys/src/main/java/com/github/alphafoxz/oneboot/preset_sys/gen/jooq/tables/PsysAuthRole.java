/*
 * This file is generated by jOOQ.
 */
package com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables;


import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.Keys;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.PresetSys;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysAuthRoleRecord;

import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function6;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row6;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;


/**
 * 角色表
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PsysAuthRole extends TableImpl<PsysAuthRoleRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>preset_sys.psys_auth_role</code>
     */
    public static final PsysAuthRole PSYS_AUTH_ROLE = new PsysAuthRole();

    /**
     * The class holding records for this type
     */
    @Override
    @NonNull
    public Class<PsysAuthRoleRecord> getRecordType() {
        return PsysAuthRoleRecord.class;
    }

    /**
     * The column <code>preset_sys.psys_auth_role.id</code>. 主键
     */
    public final TableField<PsysAuthRoleRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false), this, "主键");

    /**
     * The column <code>preset_sys.psys_auth_role.role_title</code>. 角色名称
     */
    public final TableField<PsysAuthRoleRecord, String> ROLE_TITLE = createField(DSL.name("role_title"), SQLDataType.VARCHAR(50).nullable(false), this, "角色名称");

    /**
     * The column <code>preset_sys.psys_auth_role.role_name</code>.
     */
    public final TableField<PsysAuthRoleRecord, Integer> ROLE_NAME = createField(DSL.name("role_name"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>preset_sys.psys_auth_role.sort</code>. 排序
     */
    public final TableField<PsysAuthRoleRecord, Integer> SORT = createField(DSL.name("sort"), SQLDataType.INTEGER.nullable(false), this, "排序");

    /**
     * The column <code>preset_sys.psys_auth_role.description</code>. 描述
     */
    public final TableField<PsysAuthRoleRecord, String> DESCRIPTION = createField(DSL.name("description"), SQLDataType.VARCHAR(300), this, "描述");

    /**
     * The column <code>preset_sys.psys_auth_role.enabled</code>. 是否启用
     */
    public final TableField<PsysAuthRoleRecord, Boolean> ENABLED = createField(DSL.name("enabled"), SQLDataType.BOOLEAN, this, "是否启用");

    private PsysAuthRole(Name alias, Table<PsysAuthRoleRecord> aliased) {
        this(alias, aliased, null);
    }

    private PsysAuthRole(Name alias, Table<PsysAuthRoleRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("角色表"), TableOptions.table());
    }

    /**
     * Create an aliased <code>preset_sys.psys_auth_role</code> table reference
     */
    public PsysAuthRole(String alias) {
        this(DSL.name(alias), PSYS_AUTH_ROLE);
    }

    /**
     * Create an aliased <code>preset_sys.psys_auth_role</code> table reference
     */
    public PsysAuthRole(Name alias) {
        this(alias, PSYS_AUTH_ROLE);
    }

    /**
     * Create a <code>preset_sys.psys_auth_role</code> table reference
     */
    public PsysAuthRole() {
        this(DSL.name("psys_auth_role"), null);
    }

    public <O extends Record> PsysAuthRole(Table<O> child, ForeignKey<O, PsysAuthRoleRecord> key) {
        super(child, key, PSYS_AUTH_ROLE);
    }

    @Override
    @Nullable
    public Schema getSchema() {
        return aliased() ? null : PresetSys.PRESET_SYS;
    }

    @Override
    @NonNull
    public UniqueKey<PsysAuthRoleRecord> getPrimaryKey() {
        return Keys.PSYS_AUTH_ROLE_PK;
    }

    @Override
    @NonNull
    public PsysAuthRole as(String alias) {
        return new PsysAuthRole(DSL.name(alias), this);
    }

    @Override
    @NonNull
    public PsysAuthRole as(Name alias) {
        return new PsysAuthRole(alias, this);
    }

    @Override
    @NonNull
    public PsysAuthRole as(Table<?> alias) {
        return new PsysAuthRole(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    @NonNull
    public PsysAuthRole rename(String name) {
        return new PsysAuthRole(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    @NonNull
    public PsysAuthRole rename(Name name) {
        return new PsysAuthRole(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    @NonNull
    public PsysAuthRole rename(Table<?> name) {
        return new PsysAuthRole(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    @NonNull
    public Row6<Long, String, Integer, Integer, String, Boolean> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function6<? super Long, ? super String, ? super Integer, ? super Integer, ? super String, ? super Boolean, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function6<? super Long, ? super String, ? super Integer, ? super Integer, ? super String, ? super Boolean, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}