namespace java com.github.alphafoxz.oneboot.sdk.gen.thrift.structs
namespace rs thrift.structs

struct SdkListResponseStruct {
    1:required i64 id
    2:required i64 taskId
    3:optional list<string> data
}

struct SdkMapResponseStruct {
    1:required i64 id
    2:required i64 taskId
    3:optional map<string, string> data
}

struct SdkStringResponseStruct {
    1:required i64 id
    2:required i64 taskId
    3:optional string data
}

struct SdkLongResponseStruct {
    1:required i64 id
    2:required i64 taskId
    3:optional i64 data
}