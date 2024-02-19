package com.github.alphafoxz.oneboot.preset_sys.gen.restl.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.Set;
import com.github.alphafoxz.oneboot.preset_sys.gen.restl.enums.PsysAbacDynamicAuthTypeEnum;

@Schema(name = "PsysAbacDynamicAuthDto", description = "Abac动态授权信息")
@Accessors(chain = true)
@Getter
@Setter
public class PsysAbacDynamicAuthDto {
    @Schema(name = "id", description = "主键")
    private String id;
    /**
     * @see PsysAbacDynamicAuthTypeEnum
     */
    @Schema(name = "authorizationType", description = "授权类型")
    private Integer authorizationType;
    @Schema(name = "subjectAttrSet", description = "授权主体属性集")
    private Set<String> subjectAttrSet;
    @Schema(name = "timeoutUntil", description = "授权过期时间 yyyy-MM-dd HH:mm:ss")
    private String timeoutUntil;
    @Schema(name = "resourceId", description = "资源Id")
    private String resourceId;
    @Schema(name = "ownerSubjectId", description = "资源所有者主体Id")
    private String ownerSubjectId;
    @Schema(name = "targetSubjectId", description = "授权目标主体Id")
    private String targetSubjectId;
}