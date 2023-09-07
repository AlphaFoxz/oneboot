namespace java com.github.alphafoxz.oneboot.sdk.gen.restful.apis
include "../../../data/sdk/dtos/SdkResponseDto.thrift"

//@uri(/_sdk)
/*Sdk模块基本信息接口*/
service SdkInfoApi {
    //@getUri(/info/rootPath)
    /*获取当前项目的硬盘根目录*/
    SdkResponseDto.SdkStringResponseDto rootPath()
    //@getUri(/info/checkThriftErr)
    /*检查项目错误*/
    SdkResponseDto.SdkListResponseDto checkThriftErr()
    //@getUri(/info/checkRestApiImplements)
    /*检查RestApi实现情况*/
    SdkResponseDto.SdkListResponseDto checkRestApiImplements()
    //@getUri(/info/checkRpcImplements)
    /*检查Rpc实现情况*/
    SdkResponseDto.SdkListResponseDto checkRpcImplements()
}