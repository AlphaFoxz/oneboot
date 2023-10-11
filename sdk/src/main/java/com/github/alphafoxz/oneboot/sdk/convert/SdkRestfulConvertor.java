package com.github.alphafoxz.oneboot.sdk.convert;

import com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.*;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SdkRestfulConvertor {
    SdkRestfulConvertor INSTANCE = Mappers.getMapper(SdkRestfulConvertor.class);

    SdkStringResponseDto fromThriftSdkStringResponseDto(com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkStringResponseDto source);

    SdkMapResponseDto fromThriftSdkMapResponseDto(com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkMapResponseDto source);

    SdkListResponseDto fromThriftSdkListResponseDto(com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkListResponseDto source);

    SdkDoubleResponseDto fromThriftSdkDoubleResponseDto(com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkDoubleResponseDto source);

    SdkLongResponseDto fromThriftSdkLongResponseDto(com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkLongResponseDto source);

    SdkFileTreeResponseDto fromThriftSdkFileTreeResponseDto(com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkFileTreeResponseDto source);

    SdkFileInfoDto fromThriftSdkFileInfoDto(com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkFileInfoDto source);

    SdkCodeTemplateResponseDto fromThriftSdkCodeTemplateResponseDto(com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkCodeTemplateResponseDto source);
}
