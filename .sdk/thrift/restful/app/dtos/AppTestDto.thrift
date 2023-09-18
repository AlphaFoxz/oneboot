namespace java com.github.alphafoxz.oneboot.app.gen.restful.dtos
namespace js gen.app.dtos

include "../enums/AppTestEnum.thrift"

//测试请求参数体
/*请求参数体说明*/
struct AppTestInfoDto {
    /*主键*/
    1:required i64 id
    2:required string testJson
    3:required string testDate
    4:required string testTimestamp
    5:required bool testBool
    6:required double testDouble
    7:required double testFloat
    8:required string testTimestamptz
    9:required string testTime
    10:required string testTimetz
    11:required string testDaterange
    12:required string testVarchar50
}