namespace java com.github.alphafoxz.oneboot.sdk.gen.restful.apis
include "../../../data/sdk/dtos/SdkResponseDto.thrift"

//@uri(/_sdk/thrift)
/*thrift功能接口（rpc跨语言通信）*/
service SdkThriftApi {
    //@getUri(/getServerPort)
    /*获取当前thrift服务端口*/
    SdkResponseDto.SdkLongResponseDto getServerPort()
    //@getUri(/getExecutableFilePath)
    /*获取thrift可执行文件路径*/
    SdkResponseDto.SdkStringResponseDto getExecutableFilePath()
    //@getUri(/getTemplateContentByPath)
    /*根据文件路径获取thrift模板内容*/
    SdkResponseDto.SdkThriftTemplateResponseDto getTemplateContentByPath(1:required string filePath)
}