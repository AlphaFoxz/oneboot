/*
 * This file is generated by jOOQ.
 */
package com.github.alphafoxz.oneboot.preset_sys.gen.jooq;


import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysUser;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysUserRecord;
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

    public static final UniqueKey<PsysUserRecord> PSYS_USER_PK = Internal.createUniqueKey(PsysUser.PSYS_USER, DSL.name("psys_user_pk"), new TableField[] { PsysUser.PSYS_USER.ID }, true);
}
