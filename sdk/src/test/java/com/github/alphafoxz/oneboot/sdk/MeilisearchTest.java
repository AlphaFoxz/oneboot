//package com.github.alphafoxz.oneboot.sdk;
//
//import cn.hutool.core.date.DateField;
//import cn.hutool.json.JSONObject;
//import com.github.alphafoxz.oneboot.common.standard.starter.meilisearch.MeilisearchService;
//import com.github.alphafoxz.oneboot.common.toolkit.coding.CollUtil;
//import com.github.alphafoxz.oneboot.common.toolkit.coding.JSONUtil;
//import com.github.alphafoxz.oneboot.common.toolkit.coding.RandomUtil;
//import jakarta.annotation.Resource;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class MeilisearchTest {
//    @Resource
//    private MeilisearchService meilisearchService;
//
//    @Test
//    public void test() {
//        meilisearchService.deleteIndex("test0");
//    }
//
//    @Test
//    public void createApiKey() {
//        meilisearchService.createApiKey();
//        String k = meilisearchService.getApiKey();
//        System.err.println("apiKey:" + k);
//    }
//
//    @Test
//    public void addSingleThreadsTest() { // 183ms
//        ArrayList<JSONObject> docs = CollUtil.newArrayList();
//        for (int i = 0; i < 1_0000; i++) {//准备数据
//            JSONObject human = new JSONObject();
//            human.set("id", "humanName" + i);
//            human.set("code", "humanName" + i);
//            human.set("age", RandomUtil.randomInt(0, 100));
//            human.set("phone", RandomUtil.randomNumbers(11));
//            human.set("firstName", RandomUtil.randomChinese());
//            human.set("lastName", new String(new char[]{RandomUtil.randomChinese(), RandomUtil.randomChinese()}));
//            human.set("email", RandomUtil.randomNumbers(9) + "@qq.com");
//            human.set("birthday", RandomUtil.randomDate(new Date(), DateField.YEAR, 1923, 2023));
//
//            docs.add(human);
//        }
//        String docsStr = JSONUtil.toJsonStr(docs);
//
//        long startTime = System.currentTimeMillis();
//        meilisearchService.addDocuments("test0", docsStr);
//        long finishTime = System.currentTimeMillis();
//        System.err.println("总耗时:" + (finishTime - startTime) + "毫秒");
//    }
//
//    @Test
//    public void addMulThreadsTest() { // 7019ms
//        ArrayList<JSONObject> docs = CollUtil.newArrayList();
//        //准备数据
//        for (int i = 0; i < 100_0000; i++) {
//            JSONObject human = new JSONObject();
//            human.set("id", i);
//            human.set("code", "humanName" + i);
//            human.set("age", RandomUtil.randomInt(0, 100));
//            human.set("phone", RandomUtil.randomNumbers(11));
//            human.set("firstName", RandomUtil.randomChinese());
//            human.set("lastName", new String(new char[]{RandomUtil.randomChinese(), RandomUtil.randomChinese()}));
//            human.set("email", RandomUtil.randomNumbers(9) + "@qq.com");
//            human.set("birthday", RandomUtil.randomDate(new Date(), DateField.YEAR, 1923, 2023));
//
//            docs.add(human);
//        }
//
//        List<Thread> threads = CollUtil.newArrayList();
//        for (int i = 0; i < 100; i++) {
//            final int index = i;
//            Thread thread = Thread.ofVirtual().unstarted(() -> {
//                List<JSONObject> insertDocs = docs.subList(index * 1_0000, index * 1_0000 + 1_0000);
//                meilisearchService.addDocuments("test0", JSONUtil.toJsonStr(insertDocs));
//            });
//            threads.add(thread);
//        }
//
//        long startTime = System.currentTimeMillis();
//        for (Thread thread : threads) {
//            thread.start();
//        }
//        try {
//            threads.get(0).join();
//            for (Thread thread : threads) {
//                thread.join();
//            }
//        } catch (InterruptedException e) {
//            System.err.println(e.getMessage());
//        }
//        long finishTime = System.currentTimeMillis();
//        System.err.println("总耗时:" + (finishTime - startTime) + "毫秒");
//
//    }
//}
