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
    SdkResponseDto.SdkCodeTemplateResponseDto getTemplateContentByPath(1:required SdkRequestDto.SdkStringRequestDto pathDto)
    /*根据相对引用路径获取模板内容*/
    SdkResponseDto.SdkCodeTemplateResponseDto getTemplateContentByIncludePath(1:required SdkRequestDto.SdkStringRequestDto templatePathDto, 2:required string includePath)
    /*获取restfulApi模板文件树*/
    SdkResponseDto.SdkFileTreeResponseDto getRestfulTemplateFileTree()
}
