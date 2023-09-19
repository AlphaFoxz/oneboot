package com.github.alphafoxz.oneboot.preset_sys.service.human_resources.crud;

import com.github.alphafoxz.oneboot.common.interfaces.common.impl.AbstractCachedCrudService;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysHrUser;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysHrUserPo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysHrUserRecord;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.springframework.cache.CacheManager;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import static com.github.alphafoxz.oneboot.preset_sys.gen.jooq.Tables.PSYS_HR_USER;

/**
 * PsysUser表增删改查service
 */
@Service
@Getter
@Slf4j
public class PsysHrUserCrudService extends AbstractCachedCrudService<PsysHrUser, PsysHrUserPo, PsysHrUserRecord> {
    @Resource
    private DSLContext dslContext;
    @Resource
    private CacheManager cacheManager;
    private final PsysHrUser table = PSYS_HR_USER;
    private final Class<PsysHrUserPo> poClass = PsysHrUserPo.class;

    @NonNull
    public Logger getLogger() {
        return log;
    }
}