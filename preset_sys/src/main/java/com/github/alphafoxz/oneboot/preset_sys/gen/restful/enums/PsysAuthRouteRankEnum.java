package com.github.alphafoxz.oneboot.preset_sys.gen.restful.enums;

import com.github.alphafoxz.oneboot.common.standard.restful.RestfulEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(description = "路由排序")
@AllArgsConstructor
@Getter
public enum PsysAuthRouteRankEnum implements RestfulEnum {
    /**主页*/
    HOME(0),
    DOC(1),
    UTILS(2),
    TABLE(3),
    COMPONENTS(4),
    ABLE(5),
    FRAME(6),
    NESTED(7),
    RESULT(8),
    ERROR(9),
    LIST(10),
    PERMISSION(11),
    SYSTEM(12),
    TABS(13),
    FORMDESIGN(14),
    FLOWCHART(15),
    PPT(16),
    EDITOR(17),
    GUIDE(18),
    MENUOVERFLOW(19),
    ABOUT(20);

    private final int value;
}