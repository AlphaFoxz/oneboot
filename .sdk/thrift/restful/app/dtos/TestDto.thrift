namespace java com.github.alphafoxz.oneboot.app.gen.restful.dtos
include "../enums/TestEnum.thrift"

//测试请求参数体
/*请求参数体说明*/
struct TestEditParamDto {
    /*主键*/
    1:required i64 id
    //状态枚举
    /*测试状态*/
    2:required TestEnum.TestStateEnum testState
    3:required map<string, string> optionMap
    4:required list<TestOtherInfoParamDto> otherInfoList
}

struct TestOtherInfoParamDto {
    1:required i64 id;
    2:required string name
}

struct TestResponseDto {
    1:required bool success
    2:required string msg
    3:optional TestEntityDto entity
}

struct TestEntityDto {
    1:required i64 id
    2:required string name
}