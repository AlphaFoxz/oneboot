package com.github.alphafoxz.oneboot.preset_sys.service.auth.crud;

import com.github.alphafoxz.oneboot.common.interfaces.access_control.AbacPolicy;
import com.github.alphafoxz.oneboot.common.interfaces.framework.impl.AbstractAbacCachedCrudService;
import com.github.alphafoxz.oneboot.common.toolkit.coding.ArrayUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.CollUtil;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAuthUser;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAuthUserPo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysAuthUserRecord;
import com.github.alphafoxz.oneboot.preset_sys.service.abac.policy.PsysAbacOwnerPolicy;
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
public class PsysAuthUserCrud extends AbstractAbacCachedCrudService<PsysAuthUser, PsysAuthUserPo, PsysAuthUserRecord> {
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