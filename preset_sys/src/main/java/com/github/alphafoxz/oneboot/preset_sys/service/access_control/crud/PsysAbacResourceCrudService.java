package com.github.alphafoxz.oneboot.preset_sys.service.access_control.crud;

import com.github.alphafoxz.oneboot.common.interfaces.common.impl.AbstractCachedCrudService;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAbacResource;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAbacResourcePo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysAbacResourceRecord;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.springframework.cache.CacheManager;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import static com.github.alphafoxz.oneboot.preset_sys.gen.jooq.Tables.PSYS_ABAC_RESOURCE;

/**
 * PsysAbacResource表增删改查service
 */
@Slf4j
@Getter
@Service
public class PsysAbacResourceCrudService extends AbstractCachedCrudService<PsysAbacResource, PsysAbacResourcePo, PsysAbacResourceRecord> {
    @Resource
    private DSLContext dslContext;
    @Resource
    private CacheManager cacheManager;
    private final PsysAbacResource table = PSYS_ABAC_RESOURCE;
    private final Class<PsysAbacResourcePo> poClass = PsysAbacResourcePo.class;

    @Override
    @NonNull
    public Logger getLogger() {
        return log;
    }
}