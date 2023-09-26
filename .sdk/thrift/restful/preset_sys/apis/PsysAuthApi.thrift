namespace java com.github.alphafoxz.oneboot.preset_sys.gen.restful.apis
namespace js gen.preset_sys.apis

include "../dtos/PsysAuthDto.thrift"

/*登录接口*/
//@uri(/preset_sys/auth)
service PsysAuthApi {
    /*登录接口，返回token*/
    //@postUri(/login)
    PsysAuthDto.PsysUserTokenDto login(/*用户名*/1:required PsysAuthDto.PsysLoginParam loginParam)

    /*退出登录*/
    //@getUri(/logout)
    void logout()

    /*传入旧token，如果其中的refreshToken没有过期则返回一对新的token*/
    //@postUri(/refresh)
    PsysAuthDto.PsysUserTokenDto refresh(/*旧的token*/1:required PsysAuthDto.PsysUserTokenDto oldToken)
}