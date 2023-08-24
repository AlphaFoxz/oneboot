namespace java com.github.alphafoxz.oneboot.sdk.gen.restful.apis
include "../../../data/sdk/dtos/SdkResponseDto.thrift"

/*thrift功能接口（rpc跨语言通信）*/
service SdkThriftApi {
    /*获取当前thrift服务端口*/
    SdkResponseDto.SdkLongResponseDto getServerPort()
    /*获取thrift可执行文件路径*/
    SdkResponseDto.SdkStringResponseDto getExecutableFilePath()
    /*根据文件路径获取thrift模板内容*/
    SdkResponseDto.SdkThriftTemplateResponseDto getTemplateContentByPath(1:required string filePath)
}