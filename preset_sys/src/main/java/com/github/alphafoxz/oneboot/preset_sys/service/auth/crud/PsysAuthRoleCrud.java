package com.github.alphafoxz.oneboot.preset_sys.service.auth.crud;

import com.github.alphafoxz.oneboot.common.standard.framework.impl.AbstractCachedCrudService;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAuthRole;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAuthRolePo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysAuthRoleRecord;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.springframework.cache.CacheManager;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import static com.github.alphafoxz.oneboot.preset_sys.gen.jooq.Tables.PSYS_AUTH_ROLE;

/**
 * PsysAuthRole表增删改查service
 */
@Slf4j
@Getter
@Service
public class PsysAuthRoleCrud extends AbstractCachedCrudService<PsysAuthRole, PsysAuthRolePo, PsysAuthRoleRecord> {
    @Resource
    private DSLContext dslContext;
    @Resource
    private CacheManager cacheManager;
    private final PsysAuthRole table = PSYS_AUTH_ROLE;
    private final Class<PsysAuthRolePo> poClass = PsysAuthRolePo.class;

    @Override
    @NonNull
    public Logger getLogger() {
        return log;
    }
}
