package com.github.alphafoxz.oneboot.common.annos.access_control;

import java.lang.annotation.*;

/**
 * 访问控制schema名称
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AbacResourceSchemaName {
    /**
     * 资源对应的schema名称
     */
    String value();
}
