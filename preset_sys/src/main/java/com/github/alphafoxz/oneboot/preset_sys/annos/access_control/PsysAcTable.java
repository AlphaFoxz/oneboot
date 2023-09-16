package com.github.alphafoxz.oneboot.preset_sys.annos.access_control;

import com.github.alphafoxz.oneboot.common.annos.access_control.AcResourceSchemaName;

import java.lang.annotation.*;

/**
 * 预置系统模块访问控制
 */
@AcResourceSchemaName("preset_sys")
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PsysAcTable {
    /**
     * 要访问的表名
     */
    Class<?> value();
}
