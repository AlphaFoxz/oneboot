package com.github.alphafoxz.oneboot.preset_sys.annotation;

import org.mapstruct.Mapping;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
@Mapping(target = "createTime", expression = "java(source.createTime() == null ? null : source.createTime().toInstant().toEpochMilli())")
public @interface CreateTimeDtoMapping {
}
