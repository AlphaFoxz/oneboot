/*
 * This file is generated by jOOQ.
 */
package com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables;


import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.PresetSys;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysTokenRecord;

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
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * The table <code>preset_sys.psys_token</code>.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class PsysToken extends TableImpl<PsysTokenRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>preset_sys.psys_token</code>
     */
    public static final PsysToken PSYS_TOKEN = new PsysToken();

    /**
     * The class holding records for this type
     */
    @Override
    @Nonnull
    public Class<PsysTokenRecord> getRecordType() {
        return PsysTokenRecord.class;
    }

    /**
     * The column <code>preset_sys.psys_token.id</code>. 主键
     */
    public final TableField<PsysTokenRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "主键");

    /**
     * The column <code>preset_sys.psys_token.access_token</code>. 访问令牌
     */
    public final TableField<PsysTokenRecord, String> ACCESS_TOKEN = createField(DSL.name("access_token"), SQLDataType.VARCHAR.nullable(false), this, "访问令牌");

    /**
     * The column <code>preset_sys.psys_token.refresh_token</code>. 刷新令牌
     */
    public final TableField<PsysTokenRecord, String> REFRESH_TOKEN = createField(DSL.name("refresh_token"), SQLDataType.VARCHAR.nullable(false), this, "刷新令牌");

    /**
     * The column <code>preset_sys.psys_token.create_time</code>. 创建时间
     */
    public final TableField<PsysTokenRecord, OffsetDateTime> CREATE_TIME = createField(DSL.name("create_time"), SQLDataType.TIMESTAMPWITHTIMEZONE(6).nullable(false), this, "创建时间");

    /**
     * The column <code>preset_sys.psys_token.subject_id</code>. 主体id（用户id）
     */
    public final TableField<PsysTokenRecord, Long> SUBJECT_ID = createField(DSL.name("subject_id"), SQLDataType.BIGINT.nullable(false), this, "主体id（用户id）");

    /**
     * The column <code>preset_sys.psys_token.expire_time</code>. 过期时间
     */
    public final TableField<PsysTokenRecord, OffsetDateTime> EXPIRE_TIME = createField(DSL.name("expire_time"), SQLDataType.TIMESTAMPWITHTIMEZONE(6).nullable(false), this, "过期时间");

    private PsysToken(Name alias, Table<PsysTokenRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private PsysToken(Name alias, Table<PsysTokenRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>preset_sys.psys_token</code> table reference
     */
    public PsysToken(String alias) {
        this(DSL.name(alias), PSYS_TOKEN);
    }

    /**
     * Create an aliased <code>preset_sys.psys_token</code> table reference
     */
    public PsysToken(Name alias) {
        this(alias, PSYS_TOKEN);
    }

    /**
     * Create a <code>preset_sys.psys_token</code> table reference
     */
    public PsysToken() {
        this(DSL.name("psys_token"), null);
    }

    @Override
    @Nullable
    public Schema getSchema() {
        return aliased() ? null : PresetSys.PRESET_SYS;
    }

    @Override
    @Nonnull
    public Identity<PsysTokenRecord, Long> getIdentity() {
        return (Identity<PsysTokenRecord, Long>) super.getIdentity();
    }

    @Override
    @Nonnull
    public PsysToken as(String alias) {
        return new PsysToken(DSL.name(alias), this);
    }

    @Override
    @Nonnull
    public PsysToken as(Name alias) {
        return new PsysToken(alias, this);
    }

    @Override
    @Nonnull
    public PsysToken as(Table<?> alias) {
        return new PsysToken(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public PsysToken rename(String name) {
        return new PsysToken(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public PsysToken rename(Name name) {
        return new PsysToken(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public PsysToken rename(Table<?> name) {
        return new PsysToken(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @Nonnull
    public PsysToken where(Condition condition) {
        return new PsysToken(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @Nonnull
    public PsysToken where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @Nonnull
    public PsysToken where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @Nonnull
    public PsysToken where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @Nonnull
    @PlainSQL
    public PsysToken where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @Nonnull
    @PlainSQL
    public PsysToken where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @Nonnull
    @PlainSQL
    public PsysToken where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @Nonnull
    @PlainSQL
    public PsysToken where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @Nonnull
    public PsysToken whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @Nonnull
    public PsysToken whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
