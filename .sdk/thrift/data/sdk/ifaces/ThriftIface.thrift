namespace java com.github.alphafoxz.oneboot.sdk.thrift.ifaces
namespace rs thrift.ifaces
include "../structs/ResponseStruct.thrift"

service SdkThriftIface {
    ResponseStruct.SdkListResponseStruct generateAll()
}