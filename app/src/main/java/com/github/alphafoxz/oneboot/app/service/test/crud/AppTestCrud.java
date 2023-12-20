package com.github.alphafoxz.oneboot.app.service.test.crud;

import com.github.alphafoxz.oneboot.app.gen.jooq.tables.AppTest;
import com.github.alphafoxz.oneboot.app.gen.jooq.tables.pojos.AppTestPo;
import com.github.alphafoxz.oneboot.app.gen.jooq.tables.records.AppTestRecord;
import com.github.alphafoxz.oneboot.common.standard.access_control.AbacPolicy;
import com.github.alphafoxz.oneboot.common.standard.framework.impl.AbstractAbacCachedCrudService;
import com.github.alphafoxz.oneboot.common.toolkit.coding.ArrayUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.CollUtil;
import com.github.alphafoxz.oneboot.preset_sys.service.abac.policy.PsysAbacOwnerPolicy;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.SortField;
import org.slf4j.Logger;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import static com.github.alphafoxz.oneboot.app.gen.jooq.Tables.APP_TEST;

/**
 * AppTest表增删改查service
 */
@Slf4j
@Getter
@Service
public class AppTestCrud extends AbstractAbacCachedCrudService<AppTest, AppTestPo, AppTestRecord> {
    @Resource
    private DSLContext dslContext;
    @Resource
    private CacheManager cacheManager;
    private final AppTest table = APP_TEST;
    private final Class<AppTestPo> poClass = AppTestPo.class;

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

    /**
     * 查询分页不进行权限控制
     */
    @Override
    @NonNull
    public Page<AppTestPo> selectPage(int pageNum, int pageSize, SortField<?>[] orderBy, Condition... conditions) {
        return super.selectPageWithoutAc(pageNum, pageSize, orderBy, conditions);
    }
}