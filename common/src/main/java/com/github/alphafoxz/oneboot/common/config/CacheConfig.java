package com.github.alphafoxz.oneboot.common.config;

import com.github.alphafoxz.oneboot.common.toolkit.coding.CollUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.JSONUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.SecureUtil;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.List;

@Slf4j
@Configuration
@EnableCaching
public class CacheConfig {
    public static final String QUERY_ONE = "queryOne:";

    @Bean
    public Caffeine<Object, Object> caffeine() {
        return Caffeine.newBuilder()
                .maximumSize(10_000)
                .expireAfterWrite(Duration.ofMinutes(30));
    }

    @Bean
    public CacheManager cacheManager(Caffeine<Object, Object> caffeine) {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(caffeine);
        return cacheManager;
    }

    @Bean
    public KeyGenerator serviceCacheKeyGenerator() {
        return (Object target, Method method, Object... params) -> {
            StringBuilder finalResult = new StringBuilder();
            // 必须有类名作为前缀，避免走入 Default 之后取的方法名一样造成无法类型转换
//            finalResult.append(target.getClass().getSimpleName());
//            finalResult.append(":");
//            finalResult.append(method.getName());
//            finalResult.append(":");

            if (params.length == 0) {
                finalResult.append("noParams");
                return finalResult.toString();
            }

            // 只含有一个参数位置，并且是基础类型，则进行特殊处理
            if (params.length == 1) {
                Object param = params[0];
                if (null == param) {
                    finalResult.append("nullParams");
                    return finalResult.toString();
                }
                Class<?> clazz = param.getClass();
                if (checkClassBasicType(clazz)) {
                    finalResult.append(param);
                    return finalResult.toString();
                }
            }

            // 非基础类型或多参数的场景
            StringBuilder paramString = new StringBuilder();
            for (int i = 0; i < params.length; i++) {
                Class<?> clazz = params[i].getClass();
                if (null == params[i]) {
                    paramString.append("nullParams");
                } else if (checkClassBasicType(clazz)) {
                    paramString.append(params[i]);
                } else {
                    paramString.append(JSONUtil.toJsonStr(params[i]));
                }
                if (i != params.length - 1) {
                    paramString.append(":");
                }
            }

            String finalParam = paramString.toString();
            String sha256 = SecureUtil.sha256(finalParam);

            log.debug("redisCacheConfig::serviceCacheKeyGenerator Method <{}>, Param <{}> SHA256 <{}>", method.getName(), finalParam, sha256);

            finalResult.append(sha256);
            return finalResult.toString();
        };
    }

    /**
     * 检查是否为基础类型
     */
    private Boolean checkClassBasicType(Class<?> clazz) {
        // 判断基本类型（boolean、char、byte、short、int、long、float、double）
        if (clazz.isPrimitive()) {
            return true;
        }
        // 判断原始类型
        String classTypeName = clazz.getName();
        List<String> basicTypeList = CollUtil.newArrayList(
                String.class.getName(),
                Boolean.class.getName(),
                Character.class.getName()
        );
        boolean result = basicTypeList.contains(classTypeName);
        if (!result && Number.class.isAssignableFrom(clazz)) {
            result = true;
        }
        return result;
    }
}
