namespace java com.github.alphafoxz.oneboot.sdk.gen.thrift.ifaces
namespace rs thrift.gen.ifaces
include "../dtos/SdkRequestDto.thrift"
include "../dtos/SdkResponseDto.thrift"

service SdkGenCodeIface {
    /*创建typescript的Api代码*/
    SdkResponseDto.SdkMapResponseDto previewGenerateTsApi(1:required SdkRequestDto.SdkCodeTemplateRequestDto templateDto, 2:required string genDir)
    /*创建rust的Api代码*/
    SdkResponseDto.SdkMapResponseDto previewGenerateRustApi(1:required SdkRequestDto.SdkCodeTemplateRequestDto templateDto, 2:required string genDir)
    /*创建java的Api代码*/
    SdkResponseDto.SdkListResponseDto generateJavaApi(1:required SdkRequestDto.SdkCodeTemplateRequestDto templateDto)
    /*创建全部Rpc代码*/
    SdkResponseDto.SdkListResponseDto generateJavaRpc(1:required i64 taskId)
    /*生成sql语句*/
    SdkResponseDto.SdkMapResponseDto previewGenerateSql(1:required SdkRequestDto.SdkCodeTemplateRequestDto templateDto)
}