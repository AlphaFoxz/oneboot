package com.github.alphafoxz.oneboot.preset_sys.service.access_control;

import com.github.alphafoxz.oneboot.common.ifaces.CachedCrudService;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAbacResource;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAbacResourcePo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysAbacResourceRecord;
import jakarta.annotation.Resource;
import lombok.Getter;
import org.jooq.DSLContext;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import static com.github.alphafoxz.oneboot.preset_sys.gen.jooq.Tables.PSYS_ABAC_RESOURCE;

@Service
@Getter
public class PsysAbacResourceService implements CachedCrudService<PsysAbacResource, PsysAbacResourcePo, PsysAbacResourceRecord> {
    @Resource
    private DSLContext dslContext;
    @Resource
    private CacheManager cacheManager;
    private final PsysAbacResource table = PSYS_ABAC_RESOURCE;
    private final Class<PsysAbacResourcePo> poClass = PsysAbacResourcePo.class;
}