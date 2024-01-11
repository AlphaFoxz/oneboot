package com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Schema(name = "PsysAccountUserInfoDto", description = "用户信息")
@Accessors(chain = true)
@Getter
@Setter
public class PsysAccountUserInfoDto {
    @Schema(name = "id", description = "主键")
    private Long id;
    @Schema(name = "username", description = "用户名称")
    private String username;
    @Schema(name = "nickname", description = "用户昵称")
    private String nickname;
    @Schema(name = "avatar", description = "用户头像")
    private String avatar;
    @Schema(name = "sex", description = "性别")
    private Integer sex;
    @Schema(name = "phone", description = "手机号码")
    private String phone;
    @Schema(name = "email", description = "电子邮箱")
    private String email;
    @Schema(name = "remark", description = "备注")
    private String remark;
    @Schema(name = "status", description = "状态")
    private Integer status;
    @Schema(name = "createTime", description = "创建时间")
    private Long createTime;
}