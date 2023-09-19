
package com.github.alphafoxz.oneboot.preset_sys.service.access_control.crud;

import com.github.alphafoxz.oneboot.common.interfaces.common.impl.AbstractCachedCrudService;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAbacSubject;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAbacSubjectPo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysAbacSubjectRecord;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.springframework.cache.CacheManager;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import static com.github.alphafoxz.oneboot.preset_sys.gen.jooq.Tables.PSYS_ABAC_SUBJECT;

/**
 * PsysAbacSubject表增删改查service
 */
@Slf4j
@Getter
@Service
public class PsysAbacSubjectCrudService extends AbstractCachedCrudService<PsysAbacSubject, PsysAbacSubjectPo, PsysAbacSubjectRecord> {
    @Resource
    private DSLContext dslContext;
    @Resource
    private CacheManager cacheManager;
    private final PsysAbacSubject table = PSYS_ABAC_SUBJECT;
    private final Class<PsysAbacSubjectPo> poClass = PsysAbacSubjectPo.class;

    @Override
    @NonNull
    public Logger getLogger() {
        return log;
    }
}
