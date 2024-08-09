package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.command;

import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.AbacAttr;

/**
 * 删除属性访问控制命令
 * @author Wong
 * @version 1.0
 * @created 03-7月-2024 7:16:44
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.Getter
public class DelAbacCommand {

    private AbacAttr abacAttr;
    private Long operatorSubjectId;
    private Long subjectId;
    private java.time.OffsetDateTime time;

}//end DelAbacCommand