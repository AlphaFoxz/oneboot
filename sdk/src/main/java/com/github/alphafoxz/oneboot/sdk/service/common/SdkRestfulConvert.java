package com.github.alphafoxz.oneboot.sdk.service.common;

import com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.*;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SdkRestfulConvert {
    SdkRestfulConvert INSTANCE = Mappers.getMapper(SdkRestfulConvert.class);

    SdkStringResponseDto fromThriftSdkStringResponseDto(com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkStringResponseDto source);

    SdkListResponseDto fromThriftSdkListResponseDto(com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkListResponseDto source);

    SdkCodeTemplateResponseDto fromThriftSdkCodeTemplateResponseDto(com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkCodeTemplateResponseDto source);
}
