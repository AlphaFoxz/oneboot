namespace java com.github.alphafoxz.oneboot.sdk.gen.restful.dtos
namespace js gen.sdk.dtos
namespace rs thrift.gen.dtos

struct SdkThriftTemplateDto {
    1:required string filePath
    2:required string fileSeparator
    3:required map<string, string> namespace
    4:optional string ast
    5:required string content
    6:required map<string, SdkThriftTemplateDto> includes;
}