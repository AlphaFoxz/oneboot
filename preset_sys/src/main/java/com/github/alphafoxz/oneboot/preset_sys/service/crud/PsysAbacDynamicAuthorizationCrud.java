package com.github.alphafoxz.oneboot.preset_sys.service.crud;

import com.github.alphafoxz.oneboot.common.standard.access_control.AbacPolicy;
import com.github.alphafoxz.oneboot.common.standard.framework.impl.AbstractAbacCachedCrudService;
import com.github.alphafoxz.oneboot.common.toolkit.coding.ArrayUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.CollUtil;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAbacDynamicAuthorization;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAbacDynamicAuthorizationPo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysAbacDynamicAuthorizationRecord;
import com.github.alphafoxz.oneboot.preset_sys.service.abac.policy.PsysAbacOwnerPolicy;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.springframework.cache.CacheManager;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import static com.github.alphafoxz.oneboot.preset_sys.gen.jooq.Tables.PSYS_ABAC_DYNAMIC_AUTHORIZATION;

/**
 * PsysAbacDynamicAuthorization表增删改查service
 */
@Slf4j
@Getter
@Service
public class PsysAbacDynamicAuthorizationCrud extends AbstractAbacCachedCrudService<PsysAbacDynamicAuthorization, PsysAbacDynamicAuthorizationPo, PsysAbacDynamicAuthorizationRecord> {
    @Resource
    private DSLContext dslContext;
    @Resource
    private CacheManager cacheManager;
    private final PsysAbacDynamicAuthorization table = PSYS_ABAC_DYNAMIC_AUTHORIZATION;
    private final Class<PsysAbacDynamicAuthorizationPo> poClass = PsysAbacDynamicAuthorizationPo.class;

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