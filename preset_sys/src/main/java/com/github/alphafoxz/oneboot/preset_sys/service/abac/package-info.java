/**
 * ABAC（基于属性的访问控制）的实现
 */
package com.github.alphafoxz.oneboot.preset_sys.service.abac;

import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.Tables;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAbacDynamicAuthorization;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAbacResource;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAbacResourceProtection;

class Const {
    static PsysAbacDynamicAuthorization PSYS_ABAC_DYNAMIC_AUTHORIZATION = Tables.PSYS_ABAC_DYNAMIC_AUTHORIZATION;
    static PsysAbacResource PSYS_ABAC_RESOURCE = Tables.PSYS_ABAC_RESOURCE;
    static PsysAbacResourceProtection PSYS_ABAC_RESOURCE_PROTECTION = Tables.PSYS_ABAC_RESOURCE_PROTECTION;
}
