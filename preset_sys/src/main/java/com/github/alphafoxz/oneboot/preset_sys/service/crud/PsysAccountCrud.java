package com.github.alphafoxz.oneboot.preset_sys.service.crud;

import com.github.alphafoxz.oneboot.core.standard.access_control.AbacPolicy;
import com.github.alphafoxz.oneboot.preset_sys.service.crud.impl.AbstractAbacCachedCrudService;
import com.github.alphafoxz.oneboot.core.toolkit.coding.ArrayUtil;
import com.github.alphafoxz.oneboot.core.toolkit.coding.CollUtil;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAccount;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAccountPo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysAccountRecord;
import com.github.alphafoxz.oneboot.preset_sys.service.abac.policy.PsysAbacOwnerPolicy;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.springframework.cache.CacheManager;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import static com.github.alphafoxz.oneboot.preset_sys.gen.jooq.Tables.PSYS_ACCOUNT;

/**
 * PsysAuthAccount表增删改查service
 */
@Slf4j
@Getter
@Service
public class PsysAccountCrud extends AbstractAbacCachedCrudService<PsysAccount, PsysAccountPo, PsysAccountRecord> {
    @Resource
    private DSLContext dslContext;
    @Resource
    private CacheManager cacheManager;
    private final PsysAccount table = PSYS_ACCOUNT;
    private final Class<PsysAccountPo> poClass = PsysAccountPo.class;

    @Override
    @NonNull
    public Logger getLogger() {
        return log;
    }

    @SuppressWarnings("unchecked")
    Class<? extends AbacPolicy>[] createPolicies = ArrayUtil.toArray(CollUtil.newArrayList(
            PsysAbacOwnerPolicy.class
    ), Class.class);

    @SuppressWarnings("unchecked")
    Class<? extends AbacPolicy>[] readPolicies = ArrayUtil.toArray(CollUtil.newArrayList(
            PsysAbacOwnerPolicy.class
    ), Class.class);

    @SuppressWarnings("unchecked")
    Class<? extends AbacPolicy>[] updatePolicies = ArrayUtil.toArray(CollUtil.newArrayList(
            PsysAbacOwnerPolicy.class
    ), Class.class);

    @SuppressWarnings("unchecked")
    Class<? extends AbacPolicy>[] logicDeletePolicies = ArrayUtil.toArray(CollUtil.newArrayList(
            PsysAbacOwnerPolicy.class
    ), Class.class);

    @SuppressWarnings("unchecked")
    Class<? extends AbacPolicy>[] deletePolicies = ArrayUtil.toArray(CollUtil.newArrayList(
            PsysAbacOwnerPolicy.class
    ), Class.class);
}