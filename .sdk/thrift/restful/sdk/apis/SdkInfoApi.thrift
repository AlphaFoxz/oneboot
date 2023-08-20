namespace java com.github.alphafoxz.oneboot.sdk.gen.restful.apis
include "../../../data/sdk/dtos/SdkResponseDto.thrift"

/*Sdk模块基本信息接口*/
service SdkInfoApi {
    /*获取当前项目的硬盘根目录*/
    SdkResponseDto.SdkStringResponseDto rootPath()
    /*检查项目错误*/
    SdkResponseDto.SdkListResponseDto checkErr()
}