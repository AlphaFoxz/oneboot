
package com.github.alphafoxz.oneboot.app.service.test.crud;

import com.github.alphafoxz.oneboot.app.gen.jooq.tables.AppTest;
import com.github.alphafoxz.oneboot.app.gen.jooq.tables.pojos.AppTestPo;
import com.github.alphafoxz.oneboot.app.gen.jooq.tables.records.AppTestRecord;
import com.github.alphafoxz.oneboot.common.interfaces.access_control.AbacPolicy;
import com.github.alphafoxz.oneboot.common.interfaces.common.impl.AbstractAbacCachedCrudService;
import com.github.alphafoxz.oneboot.preset_sys.service.access_control.policy.PsysAbacOwnerPolicy;
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
public class AppTestAbacCrudService extends AbstractAbacCachedCrudService<AppTest, AppTestPo, AppTestRecord> {
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
    public Class<? extends AbacPolicy>[] getCreatePolicies() {
        return new Class[]{
                PsysAbacOwnerPolicy.class
        };
    }

    @Override
    public Class<? extends AbacPolicy>[] getReadPolicies() {
        return new Class[]{
                PsysAbacOwnerPolicy.class
        };
    }

    @Override
    public Class<? extends AbacPolicy>[] getUpdatePolicies() {
        return new Class[]{
                PsysAbacOwnerPolicy.class
        };
    }

    @Override
    public Class<? extends AbacPolicy>[] getLogicDeletePolicies() {
        return new Class[]{
                PsysAbacOwnerPolicy.class
        };
    }

    @Override
    public Class<? extends AbacPolicy>[] getDeletePolicies() {
        return new Class[]{
                PsysAbacOwnerPolicy.class
        };
    }

    /**
     * 查询分页不进行权限控制
     */
    @Override
    @NonNull
    public Page<AppTestPo> selectPage(int pageNum, int pageSize, SortField<?>[] orderBy, Condition... conditions) {
        return super.selectPageWithoutAc(pageNum, pageSize, orderBy, conditions);
    }
}