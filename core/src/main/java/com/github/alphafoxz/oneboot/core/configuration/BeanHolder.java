package com.github.alphafoxz.oneboot.core.configuration;

import com.github.alphafoxz.oneboot.core.toolkit.coding.MapUtil;
import com.github.alphafoxz.oneboot.core.toolkit.coding.StrUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BeanHolder implements ApplicationContextAware {
    private static ApplicationContext context;
    private static final Map<Class<?>, Object> MOCK_REPOS_BY_TYPE = MapUtil.newHashMap();
    private static final Map<String, Object> MOCK_REPOS_BY_NAME = MapUtil.newHashMap();

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public static <T> T get(Class<T> beanClass) {
        if (context != null) {
            return context.getBean(beanClass);
        }
        var bean = MOCK_REPOS_BY_TYPE.get(beanClass);
        if (bean == null) {
            throw new BeansException("") {
                @Nullable
                @Override
                public Throwable getRootCause() {
                    return super.getRootCause();
                }
            };
        }
        return (T) bean;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public static <T> T get(String beanName) {
        if (context != null) {
            return (T) context.getBean(beanName);
        }
        var bean = MOCK_REPOS_BY_NAME.get(beanName);
        if (bean == null) {
            throw new BeansException("") {
                @Nullable
                @Override
                public Throwable getRootCause() {
                    return super.getRootCause();
                }
            };
        }
        return (T) bean;
    }

    @SuppressWarnings("unchecked")
    public static <T> void setMockBean(T bean) {
        Class<T> beanClass = (Class<T>) bean.getClass();
        if (context != null) {
            throw new Error("SpringContext Already initialized");
        }
        MOCK_REPOS_BY_TYPE.put(beanClass, bean);
        for (Class<?> interfaceClass : beanClass.getInterfaces()) {
            MOCK_REPOS_BY_TYPE.put(interfaceClass, bean);
        }
        MOCK_REPOS_BY_NAME.put(StrUtil.lowerFirst(beanClass.getSimpleName()), bean);
    }
}
