/*
 * This file is generated by jOOQ.
 */
package com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records;


import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAuthDepartment;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAuthDepartmentPo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;


/**
 * 部门机构
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PsysAuthDepartmentRecord extends UpdatableRecordImpl<PsysAuthDepartmentRecord> implements Record4<Long, String, Integer, Short> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>preset_sys.psys_auth_department.id</code>. 主键
     */
    public PsysAuthDepartmentRecord setId(@NonNull Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_auth_department.id</code>. 主键
     */
    @NotNull
    @NonNull
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>preset_sys.psys_auth_department.dept_name</code>. 部门名称
     */
    public PsysAuthDepartmentRecord setDeptName(@NonNull String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_auth_department.dept_name</code>. 部门名称
     */
    @NotNull
    @Size(max = 50)
    @NonNull
    public String getDeptName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>preset_sys.psys_auth_department.sort</code>. 排序
     */
    public PsysAuthDepartmentRecord setSort(@Nullable Integer value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_auth_department.sort</code>. 排序
     */
    @Nullable
    public Integer getSort() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>preset_sys.psys_auth_department.status</code>. 状态
     */
    public PsysAuthDepartmentRecord setStatus(@Nullable Short value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_auth_department.status</code>. 状态
     */
    @Nullable
    public Short getStatus() {
        return (Short) get(3);
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
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    @NonNull
    public Row4<Long, String, Integer, Short> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    @NonNull
    public Row4<Long, String, Integer, Short> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    @NonNull
    public Field<Long> field1() {
        return PsysAuthDepartment.PSYS_AUTH_DEPARTMENT.ID;
    }

    @Override
    @NonNull
    public Field<String> field2() {
        return PsysAuthDepartment.PSYS_AUTH_DEPARTMENT.DEPT_NAME;
    }

    @Override
    @NonNull
    public Field<Integer> field3() {
        return PsysAuthDepartment.PSYS_AUTH_DEPARTMENT.SORT;
    }

    @Override
    @NonNull
    public Field<Short> field4() {
        return PsysAuthDepartment.PSYS_AUTH_DEPARTMENT.STATUS;
    }

    @Override
    @NonNull
    public Long component1() {
        return getId();
    }

    @Override
    @NonNull
    public String component2() {
        return getDeptName();
    }

    @Override
    @Nullable
    public Integer component3() {
        return getSort();
    }

    @Override
    @Nullable
    public Short component4() {
        return getStatus();
    }

    @Override
    @NonNull
    public Long value1() {
        return getId();
    }

    @Override
    @NonNull
    public String value2() {
        return getDeptName();
    }

    @Override
    @Nullable
    public Integer value3() {
        return getSort();
    }

    @Override
    @Nullable
    public Short value4() {
        return getStatus();
    }

    @Override
    @NonNull
    public PsysAuthDepartmentRecord value1(@NonNull Long value) {
        setId(value);
        return this;
    }

    @Override
    @NonNull
    public PsysAuthDepartmentRecord value2(@NonNull String value) {
        setDeptName(value);
        return this;
    }

    @Override
    @NonNull
    public PsysAuthDepartmentRecord value3(@Nullable Integer value) {
        setSort(value);
        return this;
    }

    @Override
    @NonNull
    public PsysAuthDepartmentRecord value4(@Nullable Short value) {
        setStatus(value);
        return this;
    }

    @Override
    @NonNull
    public PsysAuthDepartmentRecord values(@NonNull Long value1, @NonNull String value2, @Nullable Integer value3, @Nullable Short value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PsysAuthDepartmentRecord
     */
    public PsysAuthDepartmentRecord() {
        super(PsysAuthDepartment.PSYS_AUTH_DEPARTMENT);
    }

    /**
     * Create a detached, initialised PsysAuthDepartmentRecord
     */
    public PsysAuthDepartmentRecord(@NonNull Long id, @NonNull String deptName, @Nullable Integer sort, @Nullable Short status) {
        super(PsysAuthDepartment.PSYS_AUTH_DEPARTMENT);

        setId(id);
        setDeptName(deptName);
        setSort(sort);
        setStatus(status);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised PsysAuthDepartmentRecord
     */
    public PsysAuthDepartmentRecord(PsysAuthDepartmentPo value) {
        super(PsysAuthDepartment.PSYS_AUTH_DEPARTMENT);

        if (value != null) {
            setId(value.id());
            setDeptName(value.deptName());
            setSort(value.sort());
            setStatus(value.status());
            resetChangedOnNotNull();
        }
    }
}
