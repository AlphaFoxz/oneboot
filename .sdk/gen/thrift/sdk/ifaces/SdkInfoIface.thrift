namespace java com.github.alphafoxz.oneboot.sdk.gen.thrift.ifaces
namespace rs thrift.gen.ifaces

include "../dtos/SdkResponseDto.thrift"
include "../dtos/SdkRequestDto.thrift"

service SdkInfoIface {
    /*检查模块错误*/
    SdkResponseDto.SdkListResponseDto checkThriftErr()
    /*检查RestApi实现情况*/
    SdkResponseDto.SdkListResponseDto checkRestApiImplements()
    /*检查Rpc实现情况*/
    SdkResponseDto.SdkListResponseDto checkRpcImplements()
    /*获取当前java项目命名空间*/
    SdkResponseDto.SdkStringResponseDto getJavaNamespace()
    /*删除指定文件*/
    SdkResponseDto.SdkListResponseDto deleteFile(1:required SdkRequestDto.SdkStringRequestDto filePath)
    /*保存指定文件*/
    SdkResponseDto.SdkStringResponseDto createOrUpdateFile(1:required SdkRequestDto.SdkStringRequestDto filePath, 2:required string fileContent)
    /*创建文件夹*/
    SdkResponseDto.SdkStringResponseDto createFolder(1:required SdkRequestDto.SdkStringRequestDto folderPath)
    /*重命名文件*/
    SdkResponseDto.SdkStringResponseDto renameFile(1:required SdkRequestDto.SdkStringRequestDto filePath, 2:required string newPath)
}
