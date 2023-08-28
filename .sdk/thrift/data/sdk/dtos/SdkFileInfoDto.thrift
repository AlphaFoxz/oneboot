namespace java com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos
namespace rs thrift.gen.dtos
include "../enums/SdkFileTypeEnum.thrift"

struct SdkFileInfoDto {
    1:required string filePath
    3:required string parentDir
    2:required string fileName
    4:optional string ext
    5:required SdkFileTypeEnum.SdkFileTypeEnum fileType
    6:required bool isReadOnly
    7:required bool isEmpty
    8:optional list<SdkFileInfoDto> children
}
