namespace java com.github.alphafoxz.oneboot.app.gen.restful.apis
include "../dtos/TestDto.thrift"

//本api为自动生成代码，请勿修改
/*测试API*/
service TestApi {
    /*查询请求*/
    TestDto.TestResponseDto query(1:required TestDto.TestEditParamDto editParam)
    //此处应该进行测试
    //看看能否实现对文件的下载
    /*下载*/
    void download(1:required string url)
}