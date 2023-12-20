package com.github.alphafoxz.oneboot.preset_sys.service.auth.crud;

import com.github.alphafoxz.oneboot.common.standard.framework.impl.AbstractCachedCrudService;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAuthUser;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAuthUserPo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysAuthUserRecord;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.springframework.cache.CacheManager;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import static com.github.alphafoxz.oneboot.preset_sys.gen.jooq.Tables.PSYS_AUTH_USER;

/**
 * PsysAuthUser表增删改查service
 */
@Slf4j
@Getter
@Service
public class PsysAuthUserCrud extends AbstractCachedCrudService<PsysAuthUser, PsysAuthUserPo, PsysAuthUserRecord> {
    @Resource
    private DSLContext dslContext;
    @Resource
    private CacheManager cacheManager;
    private final PsysAuthUser table = PSYS_AUTH_USER;
    private final Class<PsysAuthUserPo> poClass = PsysAuthUserPo.class;

    @Override
    @NonNull
    public Logger getLogger() {
        return log;
    }
}
