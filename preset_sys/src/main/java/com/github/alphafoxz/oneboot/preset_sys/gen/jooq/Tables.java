/*
 * This file is generated by jOOQ.
 */
package com.github.alphafoxz.oneboot.preset_sys.gen.jooq;


import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAbacDynamicAuthorization;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAbacGroup;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAbacResource;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAbacResourceProtection;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAbacSubject;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAuthAccount;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAuthDepartment;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAuthRole;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAuthToken;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAuthUser;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysMenu;


/**
 * Convenience access to all tables in preset_sys.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * 动态访问控制_授权表
     */
    public static final PsysAbacDynamicAuthorization PSYS_ABAC_DYNAMIC_AUTHORIZATION = PsysAbacDynamicAuthorization.PSYS_ABAC_DYNAMIC_AUTHORIZATION;

    /**
     * The table <code>preset_sys.psys_abac_group</code>.
     */
    public static final PsysAbacGroup PSYS_ABAC_GROUP = PsysAbacGroup.PSYS_ABAC_GROUP;

    /**
     * 属性访问控制_资源表
     */
    public static final PsysAbacResource PSYS_ABAC_RESOURCE = PsysAbacResource.PSYS_ABAC_RESOURCE;

    /**
     * 访问控制_资源保护表
     */
    public static final PsysAbacResourceProtection PSYS_ABAC_RESOURCE_PROTECTION = PsysAbacResourceProtection.PSYS_ABAC_RESOURCE_PROTECTION;

    /**
     * 属性访问控制_主体属性表
     */
    public static final PsysAbacSubject PSYS_ABAC_SUBJECT = PsysAbacSubject.PSYS_ABAC_SUBJECT;

    /**
     * The table <code>preset_sys.psys_auth_account</code>.
     */
    public static final PsysAuthAccount PSYS_AUTH_ACCOUNT = PsysAuthAccount.PSYS_AUTH_ACCOUNT;

    /**
     * 部门机构
     */
    public static final PsysAuthDepartment PSYS_AUTH_DEPARTMENT = PsysAuthDepartment.PSYS_AUTH_DEPARTMENT;

    /**
     * 角色表
     */
    public static final PsysAuthRole PSYS_AUTH_ROLE = PsysAuthRole.PSYS_AUTH_ROLE;

    /**
     * The table <code>preset_sys.psys_auth_token</code>.
     */
    public static final PsysAuthToken PSYS_AUTH_TOKEN = PsysAuthToken.PSYS_AUTH_TOKEN;

    /**
     * 用户表
     */
    public static final PsysAuthUser PSYS_AUTH_USER = PsysAuthUser.PSYS_AUTH_USER;

    /**
     * The table <code>preset_sys.psys_menu</code>.
     */
    public static final PsysMenu PSYS_MENU = PsysMenu.PSYS_MENU;
}
