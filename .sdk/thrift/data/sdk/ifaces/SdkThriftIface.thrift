namespace java com.github.alphafoxz.oneboot.sdk.gen.thrift.ifaces
namespace rs thrift.ifaces
include "../structs/SdkResponseStruct.thrift"

service SdkThriftIface {
    /*创建全部rpc代码*/
    SdkResponseStruct.SdkListResponseStruct generateAll()
    /*获取当前rpc服务端口*/
    SdkResponseStruct.SdkLongResponseStruct getServerPort()
    /*获取thrift可执行文件路径*/
    SdkResponseStruct.SdkStringResponseStruct getExecutableFilePath()
}