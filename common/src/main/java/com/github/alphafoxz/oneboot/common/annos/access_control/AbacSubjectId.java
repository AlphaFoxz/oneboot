package com.github.alphafoxz.oneboot.common.annos.access_control;

import java.lang.annotation.*;

/**
 * 访问控制主体Id
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface AbacSubjectId {
}
