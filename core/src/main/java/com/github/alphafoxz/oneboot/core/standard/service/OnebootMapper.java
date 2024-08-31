package com.github.alphafoxz.oneboot.core.standard.service;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.lang.annotation.*;

/**
 *
 */
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public @interface OnebootMapper {
}
