/*
 * This file is generated by jOOQ.
 */
package com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records;


import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAbacGroup;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAbacGroupPo;

import jakarta.validation.constraints.NotNull;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;
import org.springframework.lang.NonNull;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PsysAbacGroupRecord extends UpdatableRecordImpl<PsysAbacGroupRecord> implements Record3<Long, String, Long> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>preset_sys.psys_abac_group.id</code>. 主键
     */
    public PsysAbacGroupRecord setId(@NonNull Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_abac_group.id</code>. 主键
     */
    @NotNull
    @NonNull
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>preset_sys.psys_abac_group.group_attr_set</code>. 组标签集合
     */
    public PsysAbacGroupRecord setGroupAttrSet(@NonNull String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_abac_group.group_attr_set</code>. 组标签集合
     */
    @NotNull
    @NonNull
    public String getGroupAttrSet() {
        return (String) get(1);
    }

    /**
     * Setter for <code>preset_sys.psys_abac_group.business_id</code>. 业务id
     */
    public PsysAbacGroupRecord setBusinessId(@NonNull Long value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_abac_group.business_id</code>. 业务id
     */
    @NotNull
    @NonNull
    public Long getBusinessId() {
        return (Long) get(2);
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
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    @NonNull
    public Row3<Long, String, Long> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    @NonNull
    public Row3<Long, String, Long> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    @NonNull
    public Field<Long> field1() {
        return PsysAbacGroup.PSYS_ABAC_GROUP.ID;
    }

    @Override
    @NonNull
    public Field<String> field2() {
        return PsysAbacGroup.PSYS_ABAC_GROUP.GROUP_ATTR_SET;
    }

    @Override
    @NonNull
    public Field<Long> field3() {
        return PsysAbacGroup.PSYS_ABAC_GROUP.BUSINESS_ID;
    }

    @Override
    @NonNull
    public Long component1() {
        return getId();
    }

    @Override
    @NonNull
    public String component2() {
        return getGroupAttrSet();
    }

    @Override
    @NonNull
    public Long component3() {
        return getBusinessId();
    }

    @Override
    @NonNull
    public Long value1() {
        return getId();
    }

    @Override
    @NonNull
    public String value2() {
        return getGroupAttrSet();
    }

    @Override
    @NonNull
    public Long value3() {
        return getBusinessId();
    }

    @Override
    @NonNull
    public PsysAbacGroupRecord value1(@NonNull Long value) {
        setId(value);
        return this;
    }

    @Override
    @NonNull
    public PsysAbacGroupRecord value2(@NonNull String value) {
        setGroupAttrSet(value);
        return this;
    }

    @Override
    @NonNull
    public PsysAbacGroupRecord value3(@NonNull Long value) {
        setBusinessId(value);
        return this;
    }

    @Override
    @NonNull
    public PsysAbacGroupRecord values(@NonNull Long value1, @NonNull String value2, @NonNull Long value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PsysAbacGroupRecord
     */
    public PsysAbacGroupRecord() {
        super(PsysAbacGroup.PSYS_ABAC_GROUP);
    }

    /**
     * Create a detached, initialised PsysAbacGroupRecord
     */
    public PsysAbacGroupRecord(@NonNull Long id, @NonNull String groupAttrSet, @NonNull Long businessId) {
        super(PsysAbacGroup.PSYS_ABAC_GROUP);

        setId(id);
        setGroupAttrSet(groupAttrSet);
        setBusinessId(businessId);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised PsysAbacGroupRecord
     */
    public PsysAbacGroupRecord(PsysAbacGroupPo value) {
        super(PsysAbacGroup.PSYS_ABAC_GROUP);

        if (value != null) {
            setId(value.id());
            setGroupAttrSet(value.groupAttrSet());
            setBusinessId(value.businessId());
            resetChangedOnNotNull();
        }
    }
}
