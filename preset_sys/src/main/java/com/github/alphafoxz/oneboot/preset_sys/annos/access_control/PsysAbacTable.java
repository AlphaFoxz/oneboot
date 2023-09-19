package com.github.alphafoxz.oneboot.preset_sys.annos.access_control;

import com.github.alphafoxz.oneboot.common.annos.access_control.AbacResourceSchemaName;

import java.lang.annotation.*;

/**
 * 预置系统模块访问控制
 */
@AbacResourceSchemaName("preset_sys")
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PsysAbacTable {
    /**
     * 要访问的表名
     */
    Class<?> value();
}
