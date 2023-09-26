namespace java com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos
namespace js gen.preset_sys.dtos

/*用户名密码登录参数*/
struct PsysLoginParam {
    /*用户名*/
    1:required string username
    /*密码*/
    2:required string password
    /*验证码*/
    3:optional string verifyCode
}

/*用户令牌结果*/
struct PsysUserTokenDto {
    /*访问令牌*/
    1:required string accessToken
    /*刷新令牌*/
    2:required string refreshToken
}