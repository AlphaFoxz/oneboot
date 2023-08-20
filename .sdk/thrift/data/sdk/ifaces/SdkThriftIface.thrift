namespace java com.github.alphafoxz.oneboot.sdk.gen.thrift.ifaces
namespace rs thrift.ifaces
include "../dtos/SdkResponseDto.thrift"

service SdkThriftIface {
    /*获取当前rpc服务端口*/
    SdkResponseDto.SdkLongResponseDto getServerPort()
    /*获取thrift可执行文件路径*/
    SdkResponseDto.SdkStringResponseDto getExecutableFilePath()
}
