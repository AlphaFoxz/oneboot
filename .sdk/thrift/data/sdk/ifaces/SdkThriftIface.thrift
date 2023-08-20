namespace java com.github.alphafoxz.oneboot.sdk.gen.thrift.ifaces
namespace rs thrift.gen.ifaces
include "../dtos/SdkRequestDto.thrift"
include "../dtos/SdkResponseDto.thrift"

service SdkThriftIface {
    /*获取当前rpc服务端口*/
    SdkResponseDto.SdkLongResponseDto getServerPort()
    /*获取thrift可执行文件路径*/
    SdkResponseDto.SdkStringResponseDto getExecutableFilePath()
    /*根据路径获取模板内容*/
    SdkResponseDto.SdkStringResponseDto getTemplateContentByPath(1:required SdkRequestDto.SdkStringRequestDto pathDto)
}
