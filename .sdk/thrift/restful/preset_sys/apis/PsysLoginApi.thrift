namespace java com.github.alphafoxz.oneboot.preset_sys.gen.restful.apis
namespace js gen.preset_sys.apis

include "../dtos/PsysLoginParam.thrift"

/*登录接口*/
//@uri(/preset_sys/login)
service PsysLoginApi {
    /*登录接口，返回token*/
    //@postUri(/token)
    string token(/*用户名*/1:required PsysLoginParam.PsysLoginUserPasswordParam loginParam)
}