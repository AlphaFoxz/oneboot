namespace java com.github.alphafoxz.oneboot.app.gen.restful.apis
namespace js gen.app.apis

include "../dtos/AppTestDto.thrift"

//本api为自动生成代码，请勿修改

//@uri(/app/test)
/*测试API*/
service AppTestApi {
    //@getUri(/query)
    /*查询请求*/
    AppTestDto.AppTestResponseDto query(1:required AppTestDto.AppTestEditParamDto editParam)
    //此处应该进行测试
    //看看能否实现对文件的下载
    //@postUri(/download)
    /*下载*/
    void download(/*文件路径*/1:required string url)
}