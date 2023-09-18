namespace java com.github.alphafoxz.oneboot.app.gen.restful.apis
namespace js gen.app.apis

include "../dtos/AppTestDto.thrift"

//本api为自动生成代码，请勿修改

//@uri(/app/test)
/*测试API*/
service AppTestApi {
    /*查询单条*/
    //@getUri(/query/{id})
    AppTestDto.AppTestInfoDto queryOne(/*主键*/1:required i64 id)
    /*查询单条*/
    //@getUri(/queryPage/{pageNum}/{pageSize})
    AppTestDto.AppTestInfoDto queryPage(/*页码*/1:required i32 pageNum, /*每页数据量*/2:required i32 pageSize)
    /*更新*/
    //@postUri(/update)
    bool update(/*更新参数*/1:required AppTestDto.AppTestInfoDto param)
}