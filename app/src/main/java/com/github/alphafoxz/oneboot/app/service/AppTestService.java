package com.github.alphafoxz.oneboot.app.service;

import com.github.alphafoxz.oneboot.app.gen.jooq.tables.AppTest;
import com.github.alphafoxz.oneboot.app.gen.jooq.tables.pojos.AppTestPo;
import com.github.alphafoxz.oneboot.app.gen.jooq.tables.records.AppTestRecord;
import com.github.alphafoxz.oneboot.common.ifaces.CachedCrudService;
import jakarta.annotation.Resource;
import lombok.Getter;
import org.jooq.DSLContext;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import static com.github.alphafoxz.oneboot.app.gen.jooq.Tables.APP_TEST;

@Service
@Getter
public class AppTestService implements CachedCrudService<AppTest, AppTestPo, AppTestRecord> {
    @Resource
    private DSLContext dslContext;
    @Resource
    private CacheManager cacheManager;
    private final AppTest table = APP_TEST;
    private final Class<AppTestPo> poClass = AppTestPo.class;
}