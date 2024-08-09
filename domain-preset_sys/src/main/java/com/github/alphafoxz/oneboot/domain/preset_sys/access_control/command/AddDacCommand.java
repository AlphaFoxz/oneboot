package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.command;

import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.DacType;

/**
 * 添加动态访问控制命令
 * @author Wong
 * @version 1.0
 * @created 03-7月-2024 7:16:44
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.Getter
public class AddDacCommand {

    private DacType dacType;
    private java.time.OffsetDateTime expireTime;
    private Long operatorSubjectId;
    private Long ownerSubjectId;
    private Long resourceId;
    private Long targetSubjectId;
    private java.time.OffsetDateTime time;

}//end AddDacCommand