package com.github.alphafoxz.oneboot.preset_sys.annotation;

import org.mapstruct.Mapping;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
@Mapping(target = "updateTime", expression = "java(source.updateTime() == null ? null : source.updateTime().toInstant().toEpochMilli())")
public @interface UpdateTimeDtoMapping {
}
