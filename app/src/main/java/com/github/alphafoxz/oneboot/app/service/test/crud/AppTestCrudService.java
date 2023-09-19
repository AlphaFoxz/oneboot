package com.github.alphafoxz.oneboot.app.service.test.crud;

import com.github.alphafoxz.oneboot.app.gen.jooq.tables.AppTest;
import com.github.alphafoxz.oneboot.app.gen.jooq.tables.pojos.AppTestPo;
import com.github.alphafoxz.oneboot.app.gen.jooq.tables.records.AppTestRecord;
import com.github.alphafoxz.oneboot.common.annotations.access_control.AbacResourceBizId;
import com.github.alphafoxz.oneboot.common.annotations.access_control.AccessControl;
import com.github.alphafoxz.oneboot.common.interfaces.access_control.AbacActionType;
import com.github.alphafoxz.oneboot.common.interfaces.access_control.impl.AbstractAbacOwnerPolicy;
import com.github.alphafoxz.oneboot.common.interfaces.common.impl.AbstractCachedCrudService;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.springframework.cache.CacheManager;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import static com.github.alphafoxz.oneboot.app.gen.jooq.Tables.APP_TEST;

/**
 * AppTest表增删改查service
 */
@Slf4j
@Getter
@Service
public class AppTestCrudService extends AbstractCachedCrudService<AppTest, AppTestPo, AppTestRecord> {
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

    @Override
    @AccessControl(tableClass = AppTest.class, action = AbacActionType.READ, policies = {
            AbstractAbacOwnerPolicy.class
    })
    public AppTestPo selectOne(@AbacResourceBizId long id) {
        return super.selectOne(id);
    }
}