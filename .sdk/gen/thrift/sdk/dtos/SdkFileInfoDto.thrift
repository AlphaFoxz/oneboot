namespace java com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos
namespace rs thrift.gen.dtos
include "../enums/SdkFileTypeEnum.thrift"

struct SdkFileInfoDto {
    1:required string filePath
    2:required string parentDir
    3:required string fileName
    4:required string separator
    5:optional string content
    6:optional string ext
    7:required SdkFileTypeEnum.SdkFileTypeEnum fileType
    8:required bool isReadOnly
    9:required bool isEmpty
    10:optional list<SdkFileInfoDto> children
}
