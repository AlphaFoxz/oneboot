package com.github.alphafoxz.oneboot.core.standard.persistence;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface TableComment {
    /**
     * 表注释
     *
     * @return String
     */
    String value() default "";
}
