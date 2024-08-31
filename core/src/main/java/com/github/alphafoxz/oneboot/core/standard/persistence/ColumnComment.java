package com.github.alphafoxz.oneboot.core.standard.persistence;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ColumnComment {

    /**
     * 字段注释
     *
     * @return String
     */
    String value() default "";
}