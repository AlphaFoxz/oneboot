package com.github.alphafoxz.oneboot.app.controller;

import com.github.alphafoxz.oneboot.app.gen.jooq.tables.pojos.AppTestPo;
import com.github.alphafoxz.oneboot.app.gen.jooq.tables.records.AppTestRecord;
import com.github.alphafoxz.oneboot.app.service.test.crud.AppTestAbacCrudService;
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
    private AppTestAbacCrudService appTestAbacCrudService;
    @Resource
    private CacheManager cacheManager;

    @GetMapping("/debug")
    public ResponseEntity<?> debug() {
        for (String cacheName : cacheManager.getCacheNames()) {
            System.err.println("=============" + cacheName + "============");
            Cache cache = cacheManager.getCache(cacheName);
            Object cache1 = ReflectUtil.getFieldValue(cache, "cache");
            System.err.println(ReflectUtil.getFieldsValue(cache1)[1].toString());
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/query/{id}")
    public ResponseEntity<AppTestPo> query(@PathVariable Long id) {
        AppTestPo appTest = appTestAbacCrudService.selectOne(id);
        if (appTest == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(appTest);
    }

    @GetMapping("/queryPage")
    public ResponseEntity<Page<AppTestPo>> queryPage() {
        Page<AppTestPo> appTests = appTestAbacCrudService.selectPage(1, 10, DSL.trueCondition());
        return ResponseEntity.ok(appTests);
    }

    @GetMapping("/queryList")
    public ResponseEntity<List<AppTestPo>> queryList() {
        List<AppTestPo> appTest = appTestAbacCrudService.selectList(10, DSL.trueCondition());
        return ResponseEntity.ok(appTest);
    }

    @GetMapping("/insert/{id}")
    public ResponseEntity<?> insert(@PathVariable Long id) {
        AppTestRecord record = new AppTestRecord();
        record.setId(id);
        record.setTestTimestamptz(OffsetDateTime.now(ZoneId.systemDefault()));
        record.setTestVarchar50("test");
        int i = appTestAbacCrudService.insertMany(record);
        return ResponseEntity.ok(i);
    }

    @GetMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id) {
        Map<String, Object> updateMap = MapUtil.newHashMap();
        updateMap.put("id", id);
        updateMap.put("test_timestamptz", OffsetDateTime.now(ZoneId.systemDefault()));
        AppTestRecord appTestRecord = new AppTestRecord();
        appTestRecord.fromMap(updateMap);
        int i = appTestAbacCrudService.update(appTestRecord);
        return ResponseEntity.ok(i);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        int i = appTestAbacCrudService.deleteById(id);
        return ResponseEntity.ok(i);
    }

    @GetMapping("/cleanCache")
    public ResponseEntity<?> cleanCache() {
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
