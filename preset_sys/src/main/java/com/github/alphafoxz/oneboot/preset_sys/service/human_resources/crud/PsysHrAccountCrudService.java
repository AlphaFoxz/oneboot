
package com.github.alphafoxz.oneboot.preset_sys.service.human_resources.crud;

import com.github.alphafoxz.oneboot.common.interfaces.common.impl.AbstractCachedCrudService;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysHrAccount;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysHrAccountPo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysHrAccountRecord;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.springframework.cache.CacheManager;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import static com.github.alphafoxz.oneboot.preset_sys.gen.jooq.Tables.PSYS_HR_ACCOUNT;

/**
 * PsysHrAccount表增删改查service
 */
@Slf4j
@Getter
@Service
public class PsysHrAccountCrudService extends AbstractCachedCrudService<PsysHrAccount, PsysHrAccountPo, PsysHrAccountRecord> {
    @Resource
    private DSLContext dslContext;
    @Resource
    private CacheManager cacheManager;
    private final PsysHrAccount table = PSYS_HR_ACCOUNT;
    private final Class<PsysHrAccountPo> poClass = PsysHrAccountPo.class;

    @Override
    @NonNull
    public Logger getLogger() {
        return log;
    }
}