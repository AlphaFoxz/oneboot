/*
 * This file is generated by jOOQ.
 */
package com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables;


import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.Keys;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.PresetSys;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysAbacSubjectRecord;

import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function2;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row2;
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
 * 属性访问控制_主体属性表
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PsysAbacSubject extends TableImpl<PsysAbacSubjectRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>preset_sys.psys_abac_subject</code>
     */
    public static final PsysAbacSubject PSYS_ABAC_SUBJECT = new PsysAbacSubject();

    /**
     * The class holding records for this type
     */
    @Override
    @NonNull
    public Class<PsysAbacSubjectRecord> getRecordType() {
        return PsysAbacSubjectRecord.class;
    }

    /**
     * The column <code>preset_sys.psys_abac_subject.id</code>. 主键
     */
    public final TableField<PsysAbacSubjectRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false), this, "主键");

    /**
     * The column <code>preset_sys.psys_abac_subject.attr_set</code>. 主体属性集合
     */
    public final TableField<PsysAbacSubjectRecord, String> ATTR_SET = createField(DSL.name("attr_set"), SQLDataType.VARCHAR.nullable(false), this, "主体属性集合");

    private PsysAbacSubject(Name alias, Table<PsysAbacSubjectRecord> aliased) {
        this(alias, aliased, null);
    }

    private PsysAbacSubject(Name alias, Table<PsysAbacSubjectRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("属性访问控制_主体属性表"), TableOptions.table());
    }

    /**
     * Create an aliased <code>preset_sys.psys_abac_subject</code> table
     * reference
     */
    public PsysAbacSubject(String alias) {
        this(DSL.name(alias), PSYS_ABAC_SUBJECT);
    }

    /**
     * Create an aliased <code>preset_sys.psys_abac_subject</code> table
     * reference
     */
    public PsysAbacSubject(Name alias) {
        this(alias, PSYS_ABAC_SUBJECT);
    }

    /**
     * Create a <code>preset_sys.psys_abac_subject</code> table reference
     */
    public PsysAbacSubject() {
        this(DSL.name("psys_abac_subject"), null);
    }

    public <O extends Record> PsysAbacSubject(Table<O> child, ForeignKey<O, PsysAbacSubjectRecord> key) {
        super(child, key, PSYS_ABAC_SUBJECT);
    }

    @Override
    @Nullable
    public Schema getSchema() {
        return aliased() ? null : PresetSys.PRESET_SYS;
    }

    @Override
    @NonNull
    public UniqueKey<PsysAbacSubjectRecord> getPrimaryKey() {
        return Keys.PSYS_ABAC_SUBJECT_PK;
    }

    @Override
    @NonNull
    public PsysAbacSubject as(String alias) {
        return new PsysAbacSubject(DSL.name(alias), this);
    }

    @Override
    @NonNull
    public PsysAbacSubject as(Name alias) {
        return new PsysAbacSubject(alias, this);
    }

    @Override
    @NonNull
    public PsysAbacSubject as(Table<?> alias) {
        return new PsysAbacSubject(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    @NonNull
    public PsysAbacSubject rename(String name) {
        return new PsysAbacSubject(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    @NonNull
    public PsysAbacSubject rename(Name name) {
        return new PsysAbacSubject(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    @NonNull
    public PsysAbacSubject rename(Table<?> name) {
        return new PsysAbacSubject(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    @NonNull
    public Row2<Long, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function2<? super Long, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function2<? super Long, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
