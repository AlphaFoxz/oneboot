namespace java com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos
namespace rs thrift.gen.dtos
include "./SdkCodeTemplateDto.thrift"

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

struct SdkCodeTemplateRequestDto {
    1:required i64 id
    2:required i64 taskId
    3:required SdkCodeTemplateDto.SdkCodeTemplateDto data
}