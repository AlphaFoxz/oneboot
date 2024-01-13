/*
 * This file is generated by jOOQ.
 */
package com.github.alphafoxz.oneboot.preset_sys.gen.jooq;


import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAbacDynamicAuthorization;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAbacGroup;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAbacResource;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAbacResourceProtection;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAbacSubject;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAccount;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysMenu;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysUser;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysUserDepartment;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysUserRole;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysAbacDynamicAuthorizationRecord;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysAbacGroupRecord;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysAbacResourceProtectionRecord;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysAbacResourceRecord;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysAbacSubjectRecord;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysAccountRecord;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysMenuRecord;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysUserDepartmentRecord;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysUserRecord;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysUserRoleRecord;

import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * preset_sys.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<PsysAbacDynamicAuthorizationRecord> PSYS_ABAC_DYNAMIC_AUTHORIZATION_PK = Internal.createUniqueKey(PsysAbacDynamicAuthorization.PSYS_ABAC_DYNAMIC_AUTHORIZATION, DSL.name("psys_abac_dynamic_authorization_pk"), new TableField[] { PsysAbacDynamicAuthorization.PSYS_ABAC_DYNAMIC_AUTHORIZATION.ID }, true);
    public static final UniqueKey<PsysAbacGroupRecord> PSYS_ABAC_GROUP_PK = Internal.createUniqueKey(PsysAbacGroup.PSYS_ABAC_GROUP, DSL.name("psys_abac_group_pk"), new TableField[] { PsysAbacGroup.PSYS_ABAC_GROUP.ID }, true);
    public static final UniqueKey<PsysAbacResourceRecord> PSYS_ABAC_RESOURCE_PK = Internal.createUniqueKey(PsysAbacResource.PSYS_ABAC_RESOURCE, DSL.name("psys_abac_resource_pk"), new TableField[] { PsysAbacResource.PSYS_ABAC_RESOURCE.ID }, true);
    public static final UniqueKey<PsysAbacResourceProtectionRecord> PSYS_ABAC_RESOURCE_PROTECTION_PK = Internal.createUniqueKey(PsysAbacResourceProtection.PSYS_ABAC_RESOURCE_PROTECTION, DSL.name("psys_abac_resource_protection_pk"), new TableField[] { PsysAbacResourceProtection.PSYS_ABAC_RESOURCE_PROTECTION.ID }, true);
    public static final UniqueKey<PsysAbacSubjectRecord> PSYS_ABAC_SUBJECT_PK = Internal.createUniqueKey(PsysAbacSubject.PSYS_ABAC_SUBJECT, DSL.name("psys_abac_subject_pk"), new TableField[] { PsysAbacSubject.PSYS_ABAC_SUBJECT.ID }, true);
    public static final UniqueKey<PsysAccountRecord> PSYS_ACCOUNT_PK = Internal.createUniqueKey(PsysAccount.PSYS_ACCOUNT, DSL.name("psys_account_pk"), new TableField[] { PsysAccount.PSYS_ACCOUNT.ID }, true);
    public static final UniqueKey<PsysMenuRecord> PSYS_MENU_PK = Internal.createUniqueKey(PsysMenu.PSYS_MENU, DSL.name("psys_menu_pk"), new TableField[] { PsysMenu.PSYS_MENU.ID }, true);
    public static final UniqueKey<PsysUserRecord> PSYS_USER_PK = Internal.createUniqueKey(PsysUser.PSYS_USER, DSL.name("psys_user_pk"), new TableField[] { PsysUser.PSYS_USER.ID }, true);
    public static final UniqueKey<PsysUserDepartmentRecord> PSYS_USER_DEPARTMENT_PK = Internal.createUniqueKey(PsysUserDepartment.PSYS_USER_DEPARTMENT, DSL.name("psys_user_department_pk"), new TableField[] { PsysUserDepartment.PSYS_USER_DEPARTMENT.ID }, true);
    public static final UniqueKey<PsysUserRoleRecord> PSYS_USER_ROLE_PK = Internal.createUniqueKey(PsysUserRole.PSYS_USER_ROLE, DSL.name("psys_user_role_pk"), new TableField[] { PsysUserRole.PSYS_USER_ROLE.ID }, true);
}
