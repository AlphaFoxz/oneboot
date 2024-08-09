package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.command;

import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.DacType;

/**
 * 删除动态访问控制命令
 * @author Wong
 * @version 1.0
 * @created 03-7月-2024 7:16:44
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.Getter
public class DelDacCommand {

    private DacType dacType;
    private Long operatorSubjectId;
    private Long resourceId;
    private Long subjectId;
    private Long targetSubjectId;
    private java.time.OffsetDateTime time;

}//end DelDacCommand