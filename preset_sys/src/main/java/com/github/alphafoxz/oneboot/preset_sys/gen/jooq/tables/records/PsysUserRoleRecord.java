/*
 * This file is generated by jOOQ.
 */
package com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records;


import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysUserRole;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysUserRolePo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record9;
import org.jooq.Row9;
import org.jooq.impl.UpdatableRecordImpl;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;


/**
 * 角色表
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PsysUserRoleRecord extends UpdatableRecordImpl<PsysUserRoleRecord> implements Record9<Long, String, String, Boolean, Short, String, OffsetDateTime, OffsetDateTime, Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>preset_sys.psys_user_role.id</code>. 主键
     */
    public PsysUserRoleRecord setId(@NonNull Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_user_role.id</code>. 主键
     */
    @NotNull
    @NonNull
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>preset_sys.psys_user_role.role_name</code>. 角色名称
     */
    public PsysUserRoleRecord setRoleName(@NonNull String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_user_role.role_name</code>. 角色名称
     */
    @NotNull
    @Size(max = 50)
    @NonNull
    public String getRoleName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>preset_sys.psys_user_role.description</code>. 描述
     */
    public PsysUserRoleRecord setDescription(@Nullable String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_user_role.description</code>. 描述
     */
    @Size(max = 300)
    @Nullable
    public String getDescription() {
        return (String) get(2);
    }

    /**
     * Setter for <code>preset_sys.psys_user_role.enabled</code>. 是否启用
     */
    public PsysUserRoleRecord setEnabled(@Nullable Boolean value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_user_role.enabled</code>. 是否启用
     */
    @Nullable
    public Boolean getEnabled() {
        return (Boolean) get(3);
    }

    /**
     * Setter for <code>preset_sys.psys_user_role.status</code>. 状态
     */
    public PsysUserRoleRecord setStatus(@Nullable Short value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_user_role.status</code>. 状态
     */
    @Nullable
    public Short getStatus() {
        return (Short) get(4);
    }

    /**
     * Setter for <code>preset_sys.psys_user_role.role_code</code>. 角色编码
     */
    public PsysUserRoleRecord setRoleCode(@Nullable String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_user_role.role_code</code>. 角色编码
     */
    @Size(max = 50)
    @Nullable
    public String getRoleCode() {
        return (String) get(5);
    }

    /**
     * Setter for <code>preset_sys.psys_user_role.create_time</code>. 创建时间
     */
    public PsysUserRoleRecord setCreateTime(@NonNull OffsetDateTime value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_user_role.create_time</code>. 创建时间
     */
    @NotNull
    @NonNull
    public OffsetDateTime getCreateTime() {
        return (OffsetDateTime) get(6);
    }

    /**
     * Setter for <code>preset_sys.psys_user_role.update_time</code>. 更新时间
     */
    public PsysUserRoleRecord setUpdateTime(@Nullable OffsetDateTime value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_user_role.update_time</code>. 更新时间
     */
    @Nullable
    public OffsetDateTime getUpdateTime() {
        return (OffsetDateTime) get(7);
    }

    /**
     * Setter for <code>preset_sys.psys_user_role.remark</code>. varchar(100)
     */
    public PsysUserRoleRecord setRemark(@Nullable Integer value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_user_role.remark</code>. varchar(100)
     */
    @Nullable
    public Integer getRemark() {
        return (Integer) get(8);
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
    // Record9 type implementation
    // -------------------------------------------------------------------------

    @Override
    @NonNull
    public Row9<Long, String, String, Boolean, Short, String, OffsetDateTime, OffsetDateTime, Integer> fieldsRow() {
        return (Row9) super.fieldsRow();
    }

    @Override
    @NonNull
    public Row9<Long, String, String, Boolean, Short, String, OffsetDateTime, OffsetDateTime, Integer> valuesRow() {
        return (Row9) super.valuesRow();
    }

    @Override
    @NonNull
    public Field<Long> field1() {
        return PsysUserRole.PSYS_USER_ROLE.ID;
    }

    @Override
    @NonNull
    public Field<String> field2() {
        return PsysUserRole.PSYS_USER_ROLE.ROLE_NAME;
    }

    @Override
    @NonNull
    public Field<String> field3() {
        return PsysUserRole.PSYS_USER_ROLE.DESCRIPTION;
    }

    @Override
    @NonNull
    public Field<Boolean> field4() {
        return PsysUserRole.PSYS_USER_ROLE.ENABLED;
    }

    @Override
    @NonNull
    public Field<Short> field5() {
        return PsysUserRole.PSYS_USER_ROLE.STATUS;
    }

    @Override
    @NonNull
    public Field<String> field6() {
        return PsysUserRole.PSYS_USER_ROLE.ROLE_CODE;
    }

    @Override
    @NonNull
    public Field<OffsetDateTime> field7() {
        return PsysUserRole.PSYS_USER_ROLE.CREATE_TIME;
    }

    @Override
    @NonNull
    public Field<OffsetDateTime> field8() {
        return PsysUserRole.PSYS_USER_ROLE.UPDATE_TIME;
    }

    @Override
    @NonNull
    public Field<Integer> field9() {
        return PsysUserRole.PSYS_USER_ROLE.REMARK;
    }

    @Override
    @NonNull
    public Long component1() {
        return getId();
    }

    @Override
    @NonNull
    public String component2() {
        return getRoleName();
    }

    @Override
    @Nullable
    public String component3() {
        return getDescription();
    }

    @Override
    @Nullable
    public Boolean component4() {
        return getEnabled();
    }

    @Override
    @Nullable
    public Short component5() {
        return getStatus();
    }

    @Override
    @Nullable
    public String component6() {
        return getRoleCode();
    }

    @Override
    @NonNull
    public OffsetDateTime component7() {
        return getCreateTime();
    }

    @Override
    @Nullable
    public OffsetDateTime component8() {
        return getUpdateTime();
    }

    @Override
    @Nullable
    public Integer component9() {
        return getRemark();
    }

    @Override
    @NonNull
    public Long value1() {
        return getId();
    }

    @Override
    @NonNull
    public String value2() {
        return getRoleName();
    }

    @Override
    @Nullable
    public String value3() {
        return getDescription();
    }

    @Override
    @Nullable
    public Boolean value4() {
        return getEnabled();
    }

    @Override
    @Nullable
    public Short value5() {
        return getStatus();
    }

    @Override
    @Nullable
    public String value6() {
        return getRoleCode();
    }

    @Override
    @NonNull
    public OffsetDateTime value7() {
        return getCreateTime();
    }

    @Override
    @Nullable
    public OffsetDateTime value8() {
        return getUpdateTime();
    }

    @Override
    @Nullable
    public Integer value9() {
        return getRemark();
    }

    @Override
    @NonNull
    public PsysUserRoleRecord value1(@NonNull Long value) {
        setId(value);
        return this;
    }

    @Override
    @NonNull
    public PsysUserRoleRecord value2(@NonNull String value) {
        setRoleName(value);
        return this;
    }

    @Override
    @NonNull
    public PsysUserRoleRecord value3(@Nullable String value) {
        setDescription(value);
        return this;
    }

    @Override
    @NonNull
    public PsysUserRoleRecord value4(@Nullable Boolean value) {
        setEnabled(value);
        return this;
    }

    @Override
    @NonNull
    public PsysUserRoleRecord value5(@Nullable Short value) {
        setStatus(value);
        return this;
    }

    @Override
    @NonNull
    public PsysUserRoleRecord value6(@Nullable String value) {
        setRoleCode(value);
        return this;
    }

    @Override
    @NonNull
    public PsysUserRoleRecord value7(@NonNull OffsetDateTime value) {
        setCreateTime(value);
        return this;
    }

    @Override
    @NonNull
    public PsysUserRoleRecord value8(@Nullable OffsetDateTime value) {
        setUpdateTime(value);
        return this;
    }

    @Override
    @NonNull
    public PsysUserRoleRecord value9(@Nullable Integer value) {
        setRemark(value);
        return this;
    }

    @Override
    @NonNull
    public PsysUserRoleRecord values(@NonNull Long value1, @NonNull String value2, @Nullable String value3, @Nullable Boolean value4, @Nullable Short value5, @Nullable String value6, @NonNull OffsetDateTime value7, @Nullable OffsetDateTime value8, @Nullable Integer value9) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PsysUserRoleRecord
     */
    public PsysUserRoleRecord() {
        super(PsysUserRole.PSYS_USER_ROLE);
    }

    /**
     * Create a detached, initialised PsysUserRoleRecord
     */
    public PsysUserRoleRecord(@NonNull Long id, @NonNull String roleName, @Nullable String description, @Nullable Boolean enabled, @Nullable Short status, @Nullable String roleCode, @NonNull OffsetDateTime createTime, @Nullable OffsetDateTime updateTime, @Nullable Integer remark) {
        super(PsysUserRole.PSYS_USER_ROLE);

        setId(id);
        setRoleName(roleName);
        setDescription(description);
        setEnabled(enabled);
        setStatus(status);
        setRoleCode(roleCode);
        setCreateTime(createTime);
        setUpdateTime(updateTime);
        setRemark(remark);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised PsysUserRoleRecord
     */
    public PsysUserRoleRecord(PsysUserRolePo value) {
        super(PsysUserRole.PSYS_USER_ROLE);

        if (value != null) {
            setId(value.id());
            setRoleName(value.roleName());
            setDescription(value.description());
            setEnabled(value.enabled());
            setStatus(value.status());
            setRoleCode(value.roleCode());
            setCreateTime(value.createTime());
            setUpdateTime(value.updateTime());
            setRemark(value.remark());
            resetChangedOnNotNull();
        }
    }
}