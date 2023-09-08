namespace java com.github.alphafoxz.oneboot.sdk.gen.restful.apis
namespace js gen.sdk.apis

include "../dtos/SdkResponseDto.thrift"

//@uri(/_sdk/genCode)
/*Sdk模块代码生成接口*/
service SdkGenCodeApi {
    //@getUri(/generateJavaRpc)
    /*创建所有Java rpc代码*/
    SdkResponseDto.SdkListResponseDto generateJavaRpc()
}