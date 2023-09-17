package com.github.alphafoxz.oneboot.app.controller;

import com.github.alphafoxz.oneboot.app.gen.jooq.tables.pojos.AppTest;
import com.github.alphafoxz.oneboot.app.gen.jooq.tables.records.AppTestRecord;
import com.github.alphafoxz.oneboot.app.service.AppTestService;
import com.github.alphafoxz.oneboot.common.toolkit.coding.MapUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.ReflectUtil;
import jakarta.annotation.Resource;
import org.jooq.impl.DSL;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/app/test")
public class AppTestController {
    @Resource
    private AppTestService appTestService;
    @Resource
    private CacheManager cacheManager;

    @GetMapping("/cache/debug")
    public ResponseEntity<?> debug() {
        for (String cacheName : cacheManager.getCacheNames()) {
            System.err.println("=============" + cacheName + "============");
            Cache cache = cacheManager.getCache(cacheName);
            Object cache1 = ReflectUtil.getFieldValue(cache, "cache");
            System.err.println(ReflectUtil.getFieldsValue(cache1)[1].toString());
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/cache/query/{id}")
    public ResponseEntity<AppTest> query(@PathVariable Long id) {
        AppTest appTest = appTestService.queryOne(id);
        if (appTest == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(appTest);
    }

    @GetMapping("/cache/queryPage")
    public ResponseEntity<Page<AppTest>> queryPage() {
        Page<AppTest> appTests = appTestService.queryPage(1, 10, DSL.trueCondition());
        return ResponseEntity.ok(appTests);
    }

    @GetMapping("/cache/queryList")
    public ResponseEntity<List<AppTest>> queryList() {
        List<AppTest> appTest = appTestService.queryList(10, DSL.trueCondition());
        return ResponseEntity.ok(appTest);
    }

    @GetMapping("/cache/insert/{id}")
    public ResponseEntity<?> insert(@PathVariable Long id) {
        AppTestRecord record = new AppTestRecord();
        record.setId(id);
        record.setTestTimestamptz(OffsetDateTime.now(ZoneId.systemDefault()));
        record.setTestVarchar50("test");
        int i = appTestService.insertMany(record);
        return ResponseEntity.ok(i);
    }

    @GetMapping("/cache/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id) {
        Map<String, Object> updateMap = MapUtil.newHashMap();
        updateMap.put("id", id);
        updateMap.put("test_timestamptz", OffsetDateTime.now(ZoneId.systemDefault()));
        AppTestRecord appTestRecord = new AppTestRecord();
        appTestRecord.fromMap(updateMap);
        int i = appTestService.update(appTestRecord);
        return ResponseEntity.ok(i);
    }

    @GetMapping("/cache/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        int i = appTestService.deleteById(id);
        return ResponseEntity.ok(i);
    }

    @GetMapping("/cache/cleanCache")
    public ResponseEntity<?> cleanCache() {
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
