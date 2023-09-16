/*
 * This file is generated by jOOQ.
 */
package com.github.alphafoxz.oneboot.preset_sys.gen.jooq;


import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAbacResource;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAbacResourceProtection;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAbacSubject;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAcTable;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysDacAuthorization;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PresetSys extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>preset_sys</code>
     */
    public static final PresetSys PRESET_SYS = new PresetSys();

    /**
     * 属性访问控制_资源表
     */
    public final PsysAbacResource PSYS_ABAC_RESOURCE = PsysAbacResource.PSYS_ABAC_RESOURCE;

    /**
     * 访问控制_资源保护表
     */
    public final PsysAbacResourceProtection PSYS_ABAC_RESOURCE_PROTECTION = PsysAbacResourceProtection.PSYS_ABAC_RESOURCE_PROTECTION;

    /**
     * 属性访问控制_主体属性表
     */
    public final PsysAbacSubject PSYS_ABAC_SUBJECT = PsysAbacSubject.PSYS_ABAC_SUBJECT;

    /**
     * 受保护的表
     */
    public final PsysAcTable PSYS_AC_TABLE = PsysAcTable.PSYS_AC_TABLE;

    /**
     * 动态访问控制_授权表
     */
    public final PsysDacAuthorization PSYS_DAC_AUTHORIZATION = PsysDacAuthorization.PSYS_DAC_AUTHORIZATION;

    /**
     * No further instances allowed
     */
    private PresetSys() {
        super("preset_sys", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            PsysAbacResource.PSYS_ABAC_RESOURCE,
            PsysAbacResourceProtection.PSYS_ABAC_RESOURCE_PROTECTION,
            PsysAbacSubject.PSYS_ABAC_SUBJECT,
            PsysAcTable.PSYS_AC_TABLE,
            PsysDacAuthorization.PSYS_DAC_AUTHORIZATION
        );
    }
}
