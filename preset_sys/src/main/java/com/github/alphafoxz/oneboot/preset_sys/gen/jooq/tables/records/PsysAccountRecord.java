/*
 * This file is generated by jOOQ.
 */
package com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records;


import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAccount;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.time.OffsetDateTime;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * The table <code>preset_sys.psys_account</code>.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class PsysAccountRecord extends UpdatableRecordImpl<PsysAccountRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>preset_sys.psys_account.id</code>. 主键
     */
    public void setId(@Nullable Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>preset_sys.psys_account.id</code>. 主键
     */
    @Nullable
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>preset_sys.psys_account.create_time</code>. 创建时间
     */
    public void setCreateTime(@Nonnull OffsetDateTime value) {
        set(1, value);
    }

    /**
     * Getter for <code>preset_sys.psys_account.create_time</code>. 创建时间
     */
    @Nonnull
    public OffsetDateTime getCreateTime() {
        return (OffsetDateTime) get(1);
    }

    /**
     * Setter for <code>preset_sys.psys_account._version</code>. 版本
     */
    public void set_Version(@Nonnull OffsetDateTime value) {
        set(2, value);
    }

    /**
     * Getter for <code>preset_sys.psys_account._version</code>. 版本
     */
    @Nonnull
    public OffsetDateTime get_Version() {
        return (OffsetDateTime) get(2);
    }

    /**
     * Setter for <code>preset_sys.psys_account.password</code>. 密码
     */
    public void setPassword(@Nonnull String value) {
        set(3, value);
    }

    /**
     * Getter for <code>preset_sys.psys_account.password</code>. 密码
     */
    @Nonnull
    public String getPassword() {
        return (String) get(3);
    }

    /**
     * Setter for <code>preset_sys.psys_account.email</code>. 邮箱
     */
    public void setEmail(@Nullable String value) {
        set(4, value);
    }

    /**
     * Getter for <code>preset_sys.psys_account.email</code>. 邮箱
     */
    @Nullable
    public String getEmail() {
        return (String) get(4);
    }

    /**
     * Setter for <code>preset_sys.psys_account.phone</code>. 手机号
     */
    public void setPhone(@Nullable String value) {
        set(5, value);
    }

    /**
     * Getter for <code>preset_sys.psys_account.phone</code>. 手机号
     */
    @Nullable
    public String getPhone() {
        return (String) get(5);
    }

    /**
     * Setter for <code>preset_sys.psys_account.account_status</code>. 状态
     */
    public void setAccountStatus(@Nonnull String value) {
        set(6, value);
    }

    /**
     * Getter for <code>preset_sys.psys_account.account_status</code>. 状态
     */
    @Nonnull
    public String getAccountStatus() {
        return (String) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    @Nonnull
    public Record1<Long> key() {
        return (Record1) super.key();
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
    public PsysAccountRecord(@Nullable Long id, @Nonnull OffsetDateTime createTime, @Nonnull OffsetDateTime _Version, @Nonnull String password, @Nullable String email, @Nullable String phone, @Nonnull String accountStatus) {
        super(PsysAccount.PSYS_ACCOUNT);

        setId(id);
        setCreateTime(createTime);
        set_Version(_Version);
        setPassword(password);
        setEmail(email);
        setPhone(phone);
        setAccountStatus(accountStatus);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised PsysAccountRecord
     */
    public PsysAccountRecord(com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAccount value) {
        super(PsysAccount.PSYS_ACCOUNT);

        if (value != null) {
            setId(value.id());
            setCreateTime(value.createTime());
            set_Version(value._Version());
            setPassword(value.password());
            setEmail(value.email());
            setPhone(value.phone());
            setAccountStatus(value.accountStatus());
            resetChangedOnNotNull();
        }
    }
}
