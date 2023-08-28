package com.github.alphafoxz.oneboot.app.gen.convert;

import com.github.alphafoxz.oneboot.app.gen.restful.dtos.TestEditParamDto;
import com.github.alphafoxz.oneboot.app.gen.restful.dtos.TestOtherInfoParamDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TestConvert {
    TestConvert INSTANCE = Mappers.getMapper(TestConvert.class);

    @Mapping(source = "id", target = "id")
    TestEditParamDto toTestEditParamDto(TestOtherInfoParamDto dto);
}
