namespace java com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos
namespace rs thrift.dtos

struct SdkListResponseDto {
    1:required i64 id
    2:required i64 taskId
    3:required bool success
    4:optional string message
    5:optional list<string> data
}

struct SdkMapResponseDto {
    1:required i64 id
    2:required i64 taskId
    3:required bool success
    4:optional string message
    5:optional map<string, string> data
}

struct SdkLongResponseDto {
    1:required i64 id
    2:required i64 taskId
    3:required bool success
    4:optional string message
    5:optional i64 data
}

struct SdkDoubleResponseDto {
    1:required i64 id
    2:required i64 taskId
    3:required bool success
    4:optional string message
    5:optional double data
}

struct SdkBinaryResponseDto {
    1:required i64 id
    2:required i64 taskId
    3:required bool success
    4:optional string message
    5:optional binary data
}

struct SdkStringResponseDto {
    1:required i64 id
    2:required i64 taskId
    3:required bool success
    4:optional string message
    5:optional string data
}
