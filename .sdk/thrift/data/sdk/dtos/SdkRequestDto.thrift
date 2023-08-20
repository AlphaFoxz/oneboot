namespace java com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos
namespace rs thrift.dtos

struct SdkListRequestDto {
    1:required i64 id
    2:required i64 taskId
    3:required list<string> data
}

struct SdkMapRequestDto {
    1:required i64 id
    2:required i64 taskId
    3:required map<string, string> data
}

struct SdkStringRequestDto {
    1:required i64 id
    2:required i64 taskId
    3:required string data
}

struct SdkLongRequestDto {
    1:required i64 id
    2:required i64 taskId
    3:required i64 data
}