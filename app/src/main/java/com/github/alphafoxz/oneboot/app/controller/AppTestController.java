//package com.github.alphafoxz.oneboot.app.controller;
//
//import com.github.alphafoxz.oneboot.app.gen.jooq.tables.pojos.AppTestPo;
//import com.github.alphafoxz.oneboot.app.gen.jooq.tables.records.AppTestRecord;
//import com.github.alphafoxz.oneboot.app.gen.restl.apis.AppTestApi;
//import com.github.alphafoxz.oneboot.app.gen.restl.dtos.AppTestInfoDto;
//import com.github.alphafoxz.oneboot.app.service.crud.AppTestCrud;
//import com.github.alphafoxz.oneboot.core.toolkit.coding.FileUtil;
//import com.github.alphafoxz.oneboot.core.toolkit.coding.MapUtil;
//import com.github.alphafoxz.oneboot.core.toolkit.coding.ReflectUtil;
//import com.github.alphafoxz.oneboot.preset_sys.service.framework.Page;
//import jakarta.annotation.Resource;
//import org.jooq.impl.DSL;
//import org.springframework.cache.Cache;
//import org.springframework.cache.CacheManager;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.time.OffsetDateTime;
//import java.time.ZoneId;
//import java.util.List;
//import java.util.Map;
//
//@Controller
//public class AppTestController implements AppTestApi {
//    @Resource
//    private AppTestCrud appTestCrud;
//    @Resource
//    private CacheManager cacheManager;
//
//    @GetMapping("/debug")
//    public ResponseEntity<?> debug() {
//        for (String cacheName : cacheManager.getCacheNames()) {
//            System.err.println("=============" + cacheName + "============");
//            Cache cache = cacheManager.getCache(cacheName);
//            Object cache1 = ReflectUtil.getFieldValue(cache, "cache");
//            System.err.println(ReflectUtil.getFieldsValue(cache1)[1].toString());
//        }
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
//
//    @Override
//    public ResponseEntity<AppTestInfoDto> queryOne(@PathVariable Long id) {
//        AppTestPo appTest = appTestCrud.selectOne(id);
//        if (appTest == null) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok(new AppTestInfoDto());
//    }
//
//    @Override
//    public ResponseEntity<Page<AppTestInfoDto>> queryPage(Integer pageNum, Integer pageSize) {
//        org.springframework.data.domain.Page<AppTestPo> appTestPage = appTestCrud.selectPage(pageNum, pageSize, DSL.trueCondition());
//        org.springframework.data.domain.Page<AppTestInfoDto> map = appTestPage.map((testPo) -> {
//            return new AppTestInfoDto();
//        });
//        Page<AppTestInfoDto> page = new Page<>(map.getContent(), map.getPageable(), map.getTotalElements());
//        return ResponseEntity.ok(page);
//    }
//
//    @GetMapping("/queryList")
//    public ResponseEntity<List<AppTestPo>> queryList() {
//        List<AppTestPo> appTest = appTestCrud.selectList(10, DSL.trueCondition());
//        return ResponseEntity.ok(appTest);
//    }
//
//    @GetMapping("/insert/{id}")
//    public ResponseEntity<?> insert(@PathVariable Long id) {
//        AppTestRecord record = new AppTestRecord();
//        record.setId(id);
//        record.setTestTimestamptz(OffsetDateTime.now(ZoneId.systemDefault()));
//        record.setTestVarchar50("test");
//        int i = appTestCrud.insert(record);
//        return ResponseEntity.ok(i);
//    }
//
//    @GetMapping("/update/{id}")
//    public ResponseEntity<?> update(@PathVariable Long id) {
//        Map<String, Object> updateMap = MapUtil.newHashMap();
//        updateMap.put("id", id);
//        updateMap.put("test_timestamptz", OffsetDateTime.now(ZoneId.systemDefault()));
//        AppTestRecord appTestRecord = new AppTestRecord();
//        appTestRecord.fromMap(updateMap);
//        int i = appTestCrud.update(appTestRecord);
//        return ResponseEntity.ok(i);
//    }
//
//    @Override
//    public ResponseEntity<Boolean> update(AppTestInfoDto param) {
//        AppTestRecord appTestRecord = new AppTestRecord();
//        return ResponseEntity.ok(true);
//    }
//
//    @Override
//    public ResponseEntity<byte[]> download(Long id) {
//        File file = FileUtil.file("C:\\Users\\Wong\\Pictures\\Screenshots\\Screenshot 2024-01-02 225820.png");
//        String fileName = "测试下载文件.png";
//        return U.fileBodyBuilder(OK_200, fileName)
//                .body(FileUtil.readBytes(file));
//    }
//
//    @Override
//    public ResponseEntity<?> upload(MultipartFile file, String name) {
//        return ResponseEntity.ok(true);
//    }
//
//    public ResponseEntity<?> logicDelete(Long id, Integer status) {
//        AppTestRecord appTestRecord = new AppTestRecord();
//        appTestRecord.setId(id);
//        appTestCrud.update(appTestRecord);
//        return null;
//    }
//
//    @GetMapping("/delete/{id}")
//    public ResponseEntity<?> delete(@PathVariable Long id) {
//        int i = appTestCrud.deleteById(id);
//        return ResponseEntity.ok(i);
//    }
//
//    @GetMapping("/cleanCache")
//    public ResponseEntity<?> cleanCache() {
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
//}
