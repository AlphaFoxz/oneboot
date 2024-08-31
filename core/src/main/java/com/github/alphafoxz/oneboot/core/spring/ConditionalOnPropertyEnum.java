package com.github.alphafoxz.oneboot.core.spring;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * 根据枚举判断是否注入
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional({OnPropertyEnumCondition.class})
public @interface ConditionalOnPropertyEnum {
    /**
     * 配置文件name
     */
    String name();

    /**
     * 枚举类
     */
    Class<?> enumClass();

    /**
     * 需要匹配的具体枚举（大小写不敏感）
     */
    String[] includeAllValue() default {};

    String[] includeAnyValue() default {};
}
