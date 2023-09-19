package com.github.alphafoxz.oneboot.preset_sys.service.access_control.crud;

import com.github.alphafoxz.oneboot.common.interfaces.common.impl.AbstractCachedCrudService;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAbacResourceProtection;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAbacResourceProtectionPo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysAbacResourceProtectionRecord;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.springframework.cache.CacheManager;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import static com.github.alphafoxz.oneboot.preset_sys.gen.jooq.Tables.PSYS_ABAC_RESOURCE_PROTECTION;

/**
 * PsysAbacResourceProtection表增删改查service
 */
@Slf4j
@Getter
@Service
public class PsysAbacResourceProtectionCrudService extends AbstractCachedCrudService<PsysAbacResourceProtection, PsysAbacResourceProtectionPo, PsysAbacResourceProtectionRecord> {
    @Resource
    private DSLContext dslContext;
    @Resource
    private CacheManager cacheManager;
    private final PsysAbacResourceProtection table = PSYS_ABAC_RESOURCE_PROTECTION;
    private final Class<PsysAbacResourceProtectionPo> poClass = PsysAbacResourceProtectionPo.class;

    @NonNull
    public Logger getLogger() {
        return log;
    }
}