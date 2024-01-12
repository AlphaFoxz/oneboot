package com.github.alphafoxz.oneboot.preset_sys.controller;

import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.Tables;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysUser;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysUserDepartment;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysUserRole;

/**
 * 常量
 */
interface C {
    PsysUser PSYS_USER = Tables.PSYS_USER;
    PsysUserRole PSYS_USER_ROLE = Tables.PSYS_USER_ROLE;
    PsysUserDepartment PSYS_USER_DEPARTMENT = Tables.PSYS_USER_DEPARTMENT;
}