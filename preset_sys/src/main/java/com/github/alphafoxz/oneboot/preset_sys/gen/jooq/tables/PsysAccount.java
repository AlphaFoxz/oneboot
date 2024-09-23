/*
 * This file is generated by jOOQ.
 */
package com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables;


import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.Keys;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.PresetSys;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysAccountRecord;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.time.OffsetDateTime;
import java.util.Collection;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.SQL;
import org.jooq.Schema;
import org.jooq.Select;
import org.jooq.Stringly;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * The table <code>preset_sys.psys_account</code>.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class PsysAccount extends TableImpl<PsysAccountRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>preset_sys.psys_account</code>
     */
    public static final PsysAccount PSYS_ACCOUNT = new PsysAccount();

    /**
     * The class holding records for this type
     */
    @Override
    @Nonnull
    public Class<PsysAccountRecord> getRecordType() {
        return PsysAccountRecord.class;
    }

    /**
     * The column <code>preset_sys.psys_account.id</code>. 主键
     */
    public final TableField<PsysAccountRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "主键");

    /**
     * The column <code>preset_sys.psys_account.create_time</code>. 创建时间
     */
    public final TableField<PsysAccountRecord, OffsetDateTime> CREATE_TIME = createField(DSL.name("create_time"), SQLDataType.TIMESTAMPWITHTIMEZONE(6).nullable(false), this, "创建时间");

    /**
     * The column <code>preset_sys.psys_account._version</code>. 版本
     */
    public final TableField<PsysAccountRecord, OffsetDateTime> _VERSION = createField(DSL.name("_version"), SQLDataType.TIMESTAMPWITHTIMEZONE(6).nullable(false), this, "版本");

    /**
     * The column <code>preset_sys.psys_account.password</code>. 密码
     */
    public final TableField<PsysAccountRecord, String> PASSWORD = createField(DSL.name("password"), SQLDataType.VARCHAR.nullable(false), this, "密码");

    /**
     * The column <code>preset_sys.psys_account.email</code>. 邮箱
     */
    public final TableField<PsysAccountRecord, String> EMAIL = createField(DSL.name("email"), SQLDataType.VARCHAR, this, "邮箱");

    /**
     * The column <code>preset_sys.psys_account.phone</code>. 手机号
     */
    public final TableField<PsysAccountRecord, String> PHONE = createField(DSL.name("phone"), SQLDataType.VARCHAR, this, "手机号");

    /**
     * The column <code>preset_sys.psys_account.account_status</code>. 状态
     */
    public final TableField<PsysAccountRecord, String> ACCOUNT_STATUS = createField(DSL.name("account_status"), SQLDataType.VARCHAR.nullable(false), this, "状态");

    private PsysAccount(Name alias, Table<PsysAccountRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private PsysAccount(Name alias, Table<PsysAccountRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>preset_sys.psys_account</code> table reference
     */
    public PsysAccount(String alias) {
        this(DSL.name(alias), PSYS_ACCOUNT);
    }

    /**
     * Create an aliased <code>preset_sys.psys_account</code> table reference
     */
    public PsysAccount(Name alias) {
        this(alias, PSYS_ACCOUNT);
    }

    /**
     * Create a <code>preset_sys.psys_account</code> table reference
     */
    public PsysAccount() {
        this(DSL.name("psys_account"), null);
    }

    @Override
    @Nullable
    public Schema getSchema() {
        return aliased() ? null : PresetSys.PRESET_SYS;
    }

    @Override
    @Nonnull
    public Identity<PsysAccountRecord, Long> getIdentity() {
        return (Identity<PsysAccountRecord, Long>) super.getIdentity();
    }

    @Override
    @Nonnull
    public UniqueKey<PsysAccountRecord> getPrimaryKey() {
        return Keys.PSYS_ACCOUNT_PK;
    }

    @Override
    @Nonnull
    public TableField<PsysAccountRecord, OffsetDateTime> getRecordTimestamp() {
        return _VERSION;
    }

    @Override
    @Nonnull
    public PsysAccount as(String alias) {
        return new PsysAccount(DSL.name(alias), this);
    }

    @Override
    @Nonnull
    public PsysAccount as(Name alias) {
        return new PsysAccount(alias, this);
    }

    @Override
    @Nonnull
    public PsysAccount as(Table<?> alias) {
        return new PsysAccount(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public PsysAccount rename(String name) {
        return new PsysAccount(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public PsysAccount rename(Name name) {
        return new PsysAccount(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public PsysAccount rename(Table<?> name) {
        return new PsysAccount(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @Nonnull
    public PsysAccount where(Condition condition) {
        return new PsysAccount(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @Nonnull
    public PsysAccount where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @Nonnull
    public PsysAccount where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @Nonnull
    public PsysAccount where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @Nonnull
    @PlainSQL
    public PsysAccount where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @Nonnull
    @PlainSQL
    public PsysAccount where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @Nonnull
    @PlainSQL
    public PsysAccount where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @Nonnull
    @PlainSQL
    public PsysAccount where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @Nonnull
    public PsysAccount whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @Nonnull
    public PsysAccount whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}