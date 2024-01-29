/*
 * This file is generated by jOOQ.
 */
package com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables;


import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.Keys;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.PresetSys;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysUserRoleRecord;

import java.time.OffsetDateTime;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function9;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row9;
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
public class PsysUserRole extends TableImpl<PsysUserRoleRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>preset_sys.psys_user_role</code>
     */
    public static final PsysUserRole PSYS_USER_ROLE = new PsysUserRole();

    /**
     * The class holding records for this type
     */
    @Override
    @NonNull
    public Class<PsysUserRoleRecord> getRecordType() {
        return PsysUserRoleRecord.class;
    }

    /**
     * The column <code>preset_sys.psys_user_role.id</code>. 主键
     */
    public final TableField<PsysUserRoleRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false), this, "主键");

    /**
     * The column <code>preset_sys.psys_user_role.role_name</code>. 角色名称
     */
    public final TableField<PsysUserRoleRecord, String> ROLE_NAME = createField(DSL.name("role_name"), SQLDataType.VARCHAR(50).nullable(false), this, "角色名称");

    /**
     * The column <code>preset_sys.psys_user_role.description</code>. 描述
     */
    public final TableField<PsysUserRoleRecord, String> DESCRIPTION = createField(DSL.name("description"), SQLDataType.VARCHAR(300), this, "描述");

    /**
     * The column <code>preset_sys.psys_user_role.enabled</code>. 是否启用
     */
    public final TableField<PsysUserRoleRecord, Boolean> ENABLED = createField(DSL.name("enabled"), SQLDataType.BOOLEAN, this, "是否启用");

    /**
     * The column <code>preset_sys.psys_user_role.status</code>. 状态
     */
    public final TableField<PsysUserRoleRecord, Short> STATUS = createField(DSL.name("status"), SQLDataType.SMALLINT, this, "状态");

    /**
     * The column <code>preset_sys.psys_user_role.role_code</code>. 角色编码
     */
    public final TableField<PsysUserRoleRecord, String> ROLE_CODE = createField(DSL.name("role_code"), SQLDataType.VARCHAR(50), this, "角色编码");

    /**
     * The column <code>preset_sys.psys_user_role.create_time</code>. 创建时间
     */
    public final TableField<PsysUserRoleRecord, OffsetDateTime> CREATE_TIME = createField(DSL.name("create_time"), SQLDataType.TIMESTAMPWITHTIMEZONE(6).nullable(false), this, "创建时间");

    /**
     * The column <code>preset_sys.psys_user_role.update_time</code>. 更新时间
     */
    public final TableField<PsysUserRoleRecord, OffsetDateTime> UPDATE_TIME = createField(DSL.name("update_time"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "更新时间");

    /**
     * The column <code>preset_sys.psys_user_role.remark</code>. varchar(100)
     */
    public final TableField<PsysUserRoleRecord, Integer> REMARK = createField(DSL.name("remark"), SQLDataType.INTEGER, this, "varchar(100)");

    private PsysUserRole(Name alias, Table<PsysUserRoleRecord> aliased) {
        this(alias, aliased, null);
    }

    private PsysUserRole(Name alias, Table<PsysUserRoleRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("角色表"), TableOptions.table());
    }

    /**
     * Create an aliased <code>preset_sys.psys_user_role</code> table reference
     */
    public PsysUserRole(String alias) {
        this(DSL.name(alias), PSYS_USER_ROLE);
    }

    /**
     * Create an aliased <code>preset_sys.psys_user_role</code> table reference
     */
    public PsysUserRole(Name alias) {
        this(alias, PSYS_USER_ROLE);
    }

    /**
     * Create a <code>preset_sys.psys_user_role</code> table reference
     */
    public PsysUserRole() {
        this(DSL.name("psys_user_role"), null);
    }

    public <O extends Record> PsysUserRole(Table<O> child, ForeignKey<O, PsysUserRoleRecord> key) {
        super(child, key, PSYS_USER_ROLE);
    }

    @Override
    @Nullable
    public Schema getSchema() {
        return aliased() ? null : PresetSys.PRESET_SYS;
    }

    @Override
    @NonNull
    public UniqueKey<PsysUserRoleRecord> getPrimaryKey() {
        return Keys.PSYS_USER_ROLE_PK;
    }

    @Override
    @NonNull
    public PsysUserRole as(String alias) {
        return new PsysUserRole(DSL.name(alias), this);
    }

    @Override
    @NonNull
    public PsysUserRole as(Name alias) {
        return new PsysUserRole(alias, this);
    }

    @Override
    @NonNull
    public PsysUserRole as(Table<?> alias) {
        return new PsysUserRole(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    @NonNull
    public PsysUserRole rename(String name) {
        return new PsysUserRole(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    @NonNull
    public PsysUserRole rename(Name name) {
        return new PsysUserRole(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    @NonNull
    public PsysUserRole rename(Table<?> name) {
        return new PsysUserRole(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row9 type methods
    // -------------------------------------------------------------------------

    @Override
    @NonNull
    public Row9<Long, String, String, Boolean, Short, String, OffsetDateTime, OffsetDateTime, Integer> fieldsRow() {
        return (Row9) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function9<? super Long, ? super String, ? super String, ? super Boolean, ? super Short, ? super String, ? super OffsetDateTime, ? super OffsetDateTime, ? super Integer, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function9<? super Long, ? super String, ? super String, ? super Boolean, ? super Short, ? super String, ? super OffsetDateTime, ? super OffsetDateTime, ? super Integer, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}