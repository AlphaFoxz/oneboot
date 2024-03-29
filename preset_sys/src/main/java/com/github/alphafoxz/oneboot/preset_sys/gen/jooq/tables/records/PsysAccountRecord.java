/*
 * This file is generated by jOOQ.
 */
package com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records;


import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAccount;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAccountPo;

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
public class PsysAccountRecord extends UpdatableRecordImpl<PsysAccountRecord> implements Record3<Long, Boolean, Boolean> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>preset_sys.psys_account.id</code>. 主键
     */
    public PsysAccountRecord setId(@NonNull Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_account.id</code>. 主键
     */
    @NotNull
    @NonNull
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>preset_sys.psys_account.expired</code>. 是否过期
     */
    public PsysAccountRecord setExpired(@NonNull Boolean value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_account.expired</code>. 是否过期
     */
    @NotNull
    @NonNull
    public Boolean getExpired() {
        return (Boolean) get(1);
    }

    /**
     * Setter for <code>preset_sys.psys_account.enabled</code>. 是否可用
     */
    public PsysAccountRecord setEnabled(@NonNull Boolean value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_account.enabled</code>. 是否可用
     */
    @NotNull
    @NonNull
    public Boolean getEnabled() {
        return (Boolean) get(2);
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
    public Row3<Long, Boolean, Boolean> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    @NonNull
    public Row3<Long, Boolean, Boolean> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    @NonNull
    public Field<Long> field1() {
        return PsysAccount.PSYS_ACCOUNT.ID;
    }

    @Override
    @NonNull
    public Field<Boolean> field2() {
        return PsysAccount.PSYS_ACCOUNT.EXPIRED;
    }

    @Override
    @NonNull
    public Field<Boolean> field3() {
        return PsysAccount.PSYS_ACCOUNT.ENABLED;
    }

    @Override
    @NonNull
    public Long component1() {
        return getId();
    }

    @Override
    @NonNull
    public Boolean component2() {
        return getExpired();
    }

    @Override
    @NonNull
    public Boolean component3() {
        return getEnabled();
    }

    @Override
    @NonNull
    public Long value1() {
        return getId();
    }

    @Override
    @NonNull
    public Boolean value2() {
        return getExpired();
    }

    @Override
    @NonNull
    public Boolean value3() {
        return getEnabled();
    }

    @Override
    @NonNull
    public PsysAccountRecord value1(@NonNull Long value) {
        setId(value);
        return this;
    }

    @Override
    @NonNull
    public PsysAccountRecord value2(@NonNull Boolean value) {
        setExpired(value);
        return this;
    }

    @Override
    @NonNull
    public PsysAccountRecord value3(@NonNull Boolean value) {
        setEnabled(value);
        return this;
    }

    @Override
    @NonNull
    public PsysAccountRecord values(@NonNull Long value1, @NonNull Boolean value2, @NonNull Boolean value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PsysAccountRecord
     */
    public PsysAccountRecord() {
        super(PsysAccount.PSYS_ACCOUNT);
    }

    /**
     * Create a detached, initialised PsysAccountRecord
     */
    public PsysAccountRecord(@NonNull Long id, @NonNull Boolean expired, @NonNull Boolean enabled) {
        super(PsysAccount.PSYS_ACCOUNT);

        setId(id);
        setExpired(expired);
        setEnabled(enabled);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised PsysAccountRecord
     */
    public PsysAccountRecord(PsysAccountPo value) {
        super(PsysAccount.PSYS_ACCOUNT);

        if (value != null) {
            setId(value.id());
            setExpired(value.expired());
            setEnabled(value.enabled());
            resetChangedOnNotNull();
        }
    }
}
