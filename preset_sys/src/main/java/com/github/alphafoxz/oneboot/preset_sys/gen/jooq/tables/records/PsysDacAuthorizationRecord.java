/*
 * This file is generated by jOOQ.
 */
package com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records;


import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysDacAuthorization;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysDacAuthorizationPo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;


/**
 * 动态访问控制_授权表
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PsysDacAuthorizationRecord extends UpdatableRecordImpl<PsysDacAuthorizationRecord> implements Record7<Long, String, String, OffsetDateTime, Long, Long, Long> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>preset_sys.psys_dac_authorization.id</code>. 主键
     */
    public PsysDacAuthorizationRecord setId(@NonNull Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_dac_authorization.id</code>. 主键
     */
    @NotNull
    @NonNull
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for
     * <code>preset_sys.psys_dac_authorization.authorization_type</code>. 授权类型
     * 0主动 1被动
     */
    public PsysDacAuthorizationRecord setAuthorizationType(@NonNull String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for
     * <code>preset_sys.psys_dac_authorization.authorization_type</code>. 授权类型
     * 0主动 1被动
     */
    @NotNull
    @Size(max = 1)
    @NonNull
    public String getAuthorizationType() {
        return (String) get(1);
    }

    /**
     * Setter for
     * <code>preset_sys.psys_dac_authorization.subject_attr_set</code>. 授权主体属性集合
     */
    public PsysDacAuthorizationRecord setSubjectAttrSet(@NonNull String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for
     * <code>preset_sys.psys_dac_authorization.subject_attr_set</code>. 授权主体属性集合
     */
    @NotNull
    @NonNull
    public String getSubjectAttrSet() {
        return (String) get(2);
    }

    /**
     * Setter for <code>preset_sys.psys_dac_authorization.timeout_until</code>.
     * 授权过期时间
     */
    public PsysDacAuthorizationRecord setTimeoutUntil(@Nullable OffsetDateTime value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_dac_authorization.timeout_until</code>.
     * 授权过期时间
     */
    @Nullable
    public OffsetDateTime getTimeoutUntil() {
        return (OffsetDateTime) get(3);
    }

    /**
     * Setter for <code>preset_sys.psys_dac_authorization.resource_id</code>.
     * 资源属性id
     */
    public PsysDacAuthorizationRecord setResourceId(@NonNull Long value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_dac_authorization.resource_id</code>.
     * 资源属性id
     */
    @NotNull
    @NonNull
    public Long getResourceId() {
        return (Long) get(4);
    }

    /**
     * Setter for
     * <code>preset_sys.psys_dac_authorization.owner_subject_id</code>.
     * 资源所有者主体Id
     */
    public PsysDacAuthorizationRecord setOwnerSubjectId(@NonNull Long value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for
     * <code>preset_sys.psys_dac_authorization.owner_subject_id</code>.
     * 资源所有者主体Id
     */
    @NotNull
    @NonNull
    public Long getOwnerSubjectId() {
        return (Long) get(5);
    }

    /**
     * Setter for
     * <code>preset_sys.psys_dac_authorization.target_subject_id</code>.
     * 授权目标主体Id
     */
    public PsysDacAuthorizationRecord setTargetSubjectId(@Nullable Long value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for
     * <code>preset_sys.psys_dac_authorization.target_subject_id</code>.
     * 授权目标主体Id
     */
    @Nullable
    public Long getTargetSubjectId() {
        return (Long) get(6);
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
    public Row7<Long, String, String, OffsetDateTime, Long, Long, Long> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    @Override
    @NonNull
    public Row7<Long, String, String, OffsetDateTime, Long, Long, Long> valuesRow() {
        return (Row7) super.valuesRow();
    }

    @Override
    @NonNull
    public Field<Long> field1() {
        return PsysDacAuthorization.PSYS_DAC_AUTHORIZATION.ID;
    }

    @Override
    @NonNull
    public Field<String> field2() {
        return PsysDacAuthorization.PSYS_DAC_AUTHORIZATION.AUTHORIZATION_TYPE;
    }

    @Override
    @NonNull
    public Field<String> field3() {
        return PsysDacAuthorization.PSYS_DAC_AUTHORIZATION.SUBJECT_ATTR_SET;
    }

    @Override
    @NonNull
    public Field<OffsetDateTime> field4() {
        return PsysDacAuthorization.PSYS_DAC_AUTHORIZATION.TIMEOUT_UNTIL;
    }

    @Override
    @NonNull
    public Field<Long> field5() {
        return PsysDacAuthorization.PSYS_DAC_AUTHORIZATION.RESOURCE_ID;
    }

    @Override
    @NonNull
    public Field<Long> field6() {
        return PsysDacAuthorization.PSYS_DAC_AUTHORIZATION.OWNER_SUBJECT_ID;
    }

    @Override
    @NonNull
    public Field<Long> field7() {
        return PsysDacAuthorization.PSYS_DAC_AUTHORIZATION.TARGET_SUBJECT_ID;
    }

    @Override
    @NonNull
    public Long component1() {
        return getId();
    }

    @Override
    @NonNull
    public String component2() {
        return getAuthorizationType();
    }

    @Override
    @NonNull
    public String component3() {
        return getSubjectAttrSet();
    }

    @Override
    @Nullable
    public OffsetDateTime component4() {
        return getTimeoutUntil();
    }

    @Override
    @NonNull
    public Long component5() {
        return getResourceId();
    }

    @Override
    @NonNull
    public Long component6() {
        return getOwnerSubjectId();
    }

    @Override
    @Nullable
    public Long component7() {
        return getTargetSubjectId();
    }

    @Override
    @NonNull
    public Long value1() {
        return getId();
    }

    @Override
    @NonNull
    public String value2() {
        return getAuthorizationType();
    }

    @Override
    @NonNull
    public String value3() {
        return getSubjectAttrSet();
    }

    @Override
    @Nullable
    public OffsetDateTime value4() {
        return getTimeoutUntil();
    }

    @Override
    @NonNull
    public Long value5() {
        return getResourceId();
    }

    @Override
    @NonNull
    public Long value6() {
        return getOwnerSubjectId();
    }

    @Override
    @Nullable
    public Long value7() {
        return getTargetSubjectId();
    }

    @Override
    @NonNull
    public PsysDacAuthorizationRecord value1(@NonNull Long value) {
        setId(value);
        return this;
    }

    @Override
    @NonNull
    public PsysDacAuthorizationRecord value2(@NonNull String value) {
        setAuthorizationType(value);
        return this;
    }

    @Override
    @NonNull
    public PsysDacAuthorizationRecord value3(@NonNull String value) {
        setSubjectAttrSet(value);
        return this;
    }

    @Override
    @NonNull
    public PsysDacAuthorizationRecord value4(@Nullable OffsetDateTime value) {
        setTimeoutUntil(value);
        return this;
    }

    @Override
    @NonNull
    public PsysDacAuthorizationRecord value5(@NonNull Long value) {
        setResourceId(value);
        return this;
    }

    @Override
    @NonNull
    public PsysDacAuthorizationRecord value6(@NonNull Long value) {
        setOwnerSubjectId(value);
        return this;
    }

    @Override
    @NonNull
    public PsysDacAuthorizationRecord value7(@Nullable Long value) {
        setTargetSubjectId(value);
        return this;
    }

    @Override
    @NonNull
    public PsysDacAuthorizationRecord values(@NonNull Long value1, @NonNull String value2, @NonNull String value3, @Nullable OffsetDateTime value4, @NonNull Long value5, @NonNull Long value6, @Nullable Long value7) {
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
     * Create a detached PsysDacAuthorizationRecord
     */
    public PsysDacAuthorizationRecord() {
        super(PsysDacAuthorization.PSYS_DAC_AUTHORIZATION);
    }

    /**
     * Create a detached, initialised PsysDacAuthorizationRecord
     */
    public PsysDacAuthorizationRecord(@NonNull Long id, @NonNull String authorizationType, @NonNull String subjectAttrSet, @Nullable OffsetDateTime timeoutUntil, @NonNull Long resourceId, @NonNull Long ownerSubjectId, @Nullable Long targetSubjectId) {
        super(PsysDacAuthorization.PSYS_DAC_AUTHORIZATION);

        setId(id);
        setAuthorizationType(authorizationType);
        setSubjectAttrSet(subjectAttrSet);
        setTimeoutUntil(timeoutUntil);
        setResourceId(resourceId);
        setOwnerSubjectId(ownerSubjectId);
        setTargetSubjectId(targetSubjectId);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised PsysDacAuthorizationRecord
     */
    public PsysDacAuthorizationRecord(PsysDacAuthorizationPo value) {
        super(PsysDacAuthorization.PSYS_DAC_AUTHORIZATION);

        if (value != null) {
            setId(value.id());
            setAuthorizationType(value.authorizationType());
            setSubjectAttrSet(value.subjectAttrSet());
            setTimeoutUntil(value.timeoutUntil());
            setResourceId(value.resourceId());
            setOwnerSubjectId(value.ownerSubjectId());
            setTargetSubjectId(value.targetSubjectId());
            resetChangedOnNotNull();
        }
    }
}
