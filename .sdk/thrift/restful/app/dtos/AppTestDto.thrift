namespace java com.github.alphafoxz.oneboot.app.gen.restful.dtos
namespace js gen.app.dtos

include "../enums/AppTestEnum.thrift"

//测试请求参数体
/*请求参数体说明*/
struct AppTestEditParamDto {
    /*主键*/
    1:required i64 id
    //状态枚举
    /*测试状态*/
    2:required AppTestEnum.AppTestStateEnum testState
    3:required map<string, string> optionMap
    4:required list<AppTestOtherInfoParamDto> otherInfoList
}

struct AppTestOtherInfoParamDto {
    1:required i64 id;
    2:required string name
}

struct AppTestResponseDto {
    1:required bool success
    2:required string msg
    3:optional AppTestEntityDto entity
}

struct AppTestEntityDto {
    1:required i64 id
    2:required string name
}