/*
 * This file is generated by jOOQ.
 */
package com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records;


import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAuthRole;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAuthRolePo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;


/**
 * 角色表
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PsysAuthRoleRecord extends UpdatableRecordImpl<PsysAuthRoleRecord> implements Record7<Long, String, String, String, Boolean, Short, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>preset_sys.psys_auth_role.id</code>. 主键
     */
    public PsysAuthRoleRecord setId(@NonNull Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_auth_role.id</code>. 主键
     */
    @NotNull
    @NonNull
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>preset_sys.psys_auth_role.role_num</code>. 角色编号
     */
    public PsysAuthRoleRecord setRoleNum(@Nullable String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_auth_role.role_num</code>. 角色编号
     */
    @Size(max = 50)
    @Nullable
    public String getRoleNum() {
        return (String) get(1);
    }

    /**
     * Setter for <code>preset_sys.psys_auth_role.role_name</code>. 角色名称
     */
    public PsysAuthRoleRecord setRoleName(@NonNull String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_auth_role.role_name</code>. 角色名称
     */
    @NotNull
    @Size(max = 50)
    @NonNull
    public String getRoleName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>preset_sys.psys_auth_role.description</code>. 描述
     */
    public PsysAuthRoleRecord setDescription(@Nullable String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_auth_role.description</code>. 描述
     */
    @Size(max = 300)
    @Nullable
    public String getDescription() {
        return (String) get(3);
    }

    /**
     * Setter for <code>preset_sys.psys_auth_role.enabled</code>. 是否启用
     */
    public PsysAuthRoleRecord setEnabled(@Nullable Boolean value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_auth_role.enabled</code>. 是否启用
     */
    @Nullable
    public Boolean getEnabled() {
        return (Boolean) get(4);
    }

    /**
     * Setter for <code>preset_sys.psys_auth_role.status</code>. 状态
     */
    public PsysAuthRoleRecord setStatus(@Nullable Short value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_auth_role.status</code>. 状态
     */
    @Nullable
    public Short getStatus() {
        return (Short) get(5);
    }

    /**
     * Setter for <code>preset_sys.psys_auth_role.role_code</code>. 角色bi
     */
    public PsysAuthRoleRecord setRoleCode(@Nullable String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_auth_role.role_code</code>. 角色bi
     */
    @Size(max = 50)
    @Nullable
    public String getRoleCode() {
        return (String) get(6);
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
    // Record7 type implementation
    // -------------------------------------------------------------------------

    @Override
    @NonNull
    public Row7<Long, String, String, String, Boolean, Short, String> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    @Override
    @NonNull
    public Row7<Long, String, String, String, Boolean, Short, String> valuesRow() {
        return (Row7) super.valuesRow();
    }

    @Override
    @NonNull
    public Field<Long> field1() {
        return PsysAuthRole.PSYS_AUTH_ROLE.ID;
    }

    @Override
    @NonNull
    public Field<String> field2() {
        return PsysAuthRole.PSYS_AUTH_ROLE.ROLE_NUM;
    }

    @Override
    @NonNull
    public Field<String> field3() {
        return PsysAuthRole.PSYS_AUTH_ROLE.ROLE_NAME;
    }

    @Override
    @NonNull
    public Field<String> field4() {
        return PsysAuthRole.PSYS_AUTH_ROLE.DESCRIPTION;
    }

    @Override
    @NonNull
    public Field<Boolean> field5() {
        return PsysAuthRole.PSYS_AUTH_ROLE.ENABLED;
    }

    @Override
    @NonNull
    public Field<Short> field6() {
        return PsysAuthRole.PSYS_AUTH_ROLE.STATUS;
    }

    @Override
    @NonNull
    public Field<String> field7() {
        return PsysAuthRole.PSYS_AUTH_ROLE.ROLE_CODE;
    }

    @Override
    @NonNull
    public Long component1() {
        return getId();
    }

    @Override
    @Nullable
    public String component2() {
        return getRoleNum();
    }

    @Override
    @NonNull
    public String component3() {
        return getRoleName();
    }

    @Override
    @Nullable
    public String component4() {
        return getDescription();
    }

    @Override
    @Nullable
    public Boolean component5() {
        return getEnabled();
    }

    @Override
    @Nullable
    public Short component6() {
        return getStatus();
    }

    @Override
    @Nullable
    public String component7() {
        return getRoleCode();
    }

    @Override
    @NonNull
    public Long value1() {
        return getId();
    }

    @Override
    @Nullable
    public String value2() {
        return getRoleNum();
    }

    @Override
    @NonNull
    public String value3() {
        return getRoleName();
    }

    @Override
    @Nullable
    public String value4() {
        return getDescription();
    }

    @Override
    @Nullable
    public Boolean value5() {
        return getEnabled();
    }

    @Override
    @Nullable
    public Short value6() {
        return getStatus();
    }

    @Override
    @Nullable
    public String value7() {
        return getRoleCode();
    }

    @Override
    @NonNull
    public PsysAuthRoleRecord value1(@NonNull Long value) {
        setId(value);
        return this;
    }

    @Override
    @NonNull
    public PsysAuthRoleRecord value2(@Nullable String value) {
        setRoleNum(value);
        return this;
    }

    @Override
    @NonNull
    public PsysAuthRoleRecord value3(@NonNull String value) {
        setRoleName(value);
        return this;
    }

    @Override
    @NonNull
    public PsysAuthRoleRecord value4(@Nullable String value) {
        setDescription(value);
        return this;
    }

    @Override
    @NonNull
    public PsysAuthRoleRecord value5(@Nullable Boolean value) {
        setEnabled(value);
        return this;
    }

    @Override
    @NonNull
    public PsysAuthRoleRecord value6(@Nullable Short value) {
        setStatus(value);
        return this;
    }

    @Override
    @NonNull
    public PsysAuthRoleRecord value7(@Nullable String value) {
        setRoleCode(value);
        return this;
    }

    @Override
    @NonNull
    public PsysAuthRoleRecord values(@NonNull Long value1, @Nullable String value2, @NonNull String value3, @Nullable String value4, @Nullable Boolean value5, @Nullable Short value6, @Nullable String value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PsysAuthRoleRecord
     */
    public PsysAuthRoleRecord() {
        super(PsysAuthRole.PSYS_AUTH_ROLE);
    }

    /**
     * Create a detached, initialised PsysAuthRoleRecord
     */
    public PsysAuthRoleRecord(@NonNull Long id, @Nullable String roleNum, @NonNull String roleName, @Nullable String description, @Nullable Boolean enabled, @Nullable Short status, @Nullable String roleCode) {
        super(PsysAuthRole.PSYS_AUTH_ROLE);

        setId(id);
        setRoleNum(roleNum);
        setRoleName(roleName);
        setDescription(description);
        setEnabled(enabled);
        setStatus(status);
        setRoleCode(roleCode);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised PsysAuthRoleRecord
     */
    public PsysAuthRoleRecord(PsysAuthRolePo value) {
        super(PsysAuthRole.PSYS_AUTH_ROLE);

        if (value != null) {
            setId(value.id());
            setRoleNum(value.roleNum());
            setRoleName(value.roleName());
            setDescription(value.description());
            setEnabled(value.enabled());
            setStatus(value.status());
            setRoleCode(value.roleCode());
            resetChangedOnNotNull();
        }
    }
}
