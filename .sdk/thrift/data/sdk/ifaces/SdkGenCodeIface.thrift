namespace java com.github.alphafoxz.oneboot.sdk.gen.thrift.ifaces
namespace rs thrift.ifaces
include "../structs/SdkResponseStruct.thrift"

service SdkGenCodeIface {
    /*创建所有代码*/
    SdkResponseStruct.SdkListResponseStruct generateAll()
}