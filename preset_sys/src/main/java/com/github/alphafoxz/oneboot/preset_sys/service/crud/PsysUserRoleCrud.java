package com.github.alphafoxz.oneboot.preset_sys.service.crud;

import com.github.alphafoxz.oneboot.common.standard.framework.impl.AbstractCachedCrudService;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysUserRole;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysUserRolePo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysUserRoleRecord;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.springframework.cache.CacheManager;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import static com.github.alphafoxz.oneboot.preset_sys.gen.jooq.Tables.PSYS_USER_ROLE;

/**
 * PsysAuthRole表增删改查service
 */
@Slf4j
@Getter
@Service
public class PsysUserRoleCrud extends AbstractCachedCrudService<PsysUserRole, PsysUserRolePo, PsysUserRoleRecord> {
    @Resource
    private DSLContext dslContext;
    @Resource
    private CacheManager cacheManager;
    private final PsysUserRole table = PSYS_USER_ROLE;
    private final Class<PsysUserRolePo> poClass = PsysUserRolePo.class;

    @Override
    @NonNull
    public Logger getLogger() {
        return log;
    }
}
