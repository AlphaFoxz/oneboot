namespace java com.github.alphafoxz.oneboot.sdk.gen.restful.apis
include "../../../data/sdk/dtos/SdkResponseDto.thrift"

/*Sdk模块代码生成接口*/
service SdkGenCodeApi {
    /*创建所有Java rpc代码*/
    SdkResponseDto.SdkListResponseDto generateJavaRpc()
}