namespace java com.github.alphafoxz.oneboot.sdk.thrift.structs
namespace rs thrift.structs

struct SdkListResponseStruct {
    1:required i64 id
    2:required i64 taskId
    3:required bool success
    4:optional string message
    5:optional list<string> data
}

struct SdkMapResponseStruct {
    1:required i64 id
    2:required i64 taskId
    3:required bool success
    4:optional string message
    5:optional map<string, string> data
}

struct SdkStringResponseStruct {
    1:required i64 id
    2:required i64 taskId
    3:required bool success
    4:optional string message
    5:optional map<string, string> data
}