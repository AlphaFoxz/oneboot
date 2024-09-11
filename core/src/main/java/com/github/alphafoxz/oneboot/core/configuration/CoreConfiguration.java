package com.github.alphafoxz.oneboot.core.configuration;

import com.github.alphafoxz.oneboot.core.toolkit.coding.ClassUtil;
import com.github.alphafoxz.oneboot.core.toolkit.coding.SpringUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.Annotation;

@Configuration
@RequiredArgsConstructor
@Getter
public class CoreConfiguration {
    private final CoreProperties coreProperties;

    //    @PostConstruct
    public void injectBeans() {
        var classes = ClassUtil.scanPackageByAnnotation(coreProperties.getBasePackage(), Annotation.class);
        for (var clazz : classes) {
            for (var field : clazz.getFields()) {
//                if (!field.isAnnotationPresent(InjectBean.class)) {
//                    return;
//                }
                var bean = SpringUtil.getBean(field.getDeclaringClass());
                field.setAccessible(true);
                try {
                    field.set(null, bean);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
//            for (clazz)
        }
    }


}
