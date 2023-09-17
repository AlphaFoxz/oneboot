package com.github.alphafoxz.oneboot.app.service;

import com.github.alphafoxz.oneboot.app.gen.jooq.tables.AppTest;
import com.github.alphafoxz.oneboot.app.gen.jooq.tables.records.AppTestRecord;
import com.github.alphafoxz.oneboot.common.ifaces.CachedCrudService;
import jakarta.annotation.Resource;
import lombok.Getter;
import org.jooq.DSLContext;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
@Getter
public class AppTestService implements CachedCrudService<AppTest, com.github.alphafoxz.oneboot.app.gen.jooq.tables.pojos.AppTest, AppTestRecord> {
    @Resource
    private DSLContext dslContext;
    @Resource
    private CacheManager cacheManager;
    private AppTest table = AppTest.APP_TEST;
    private Class<com.github.alphafoxz.oneboot.app.gen.jooq.tables.pojos.AppTest> poClass = com.github.alphafoxz.oneboot.app.gen.jooq.tables.pojos.AppTest.class;
}
