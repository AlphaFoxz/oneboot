package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.command;


/**
 * 编辑属性访问控制命令
 * @author Wong
 * @version 1.0
 * @created 03-7月-2024 7:16:44
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.Getter
public class EditAbacCommand {

    private int abacAttr;
    private Long operatorSubjectId;
    private Long subjectId;
    private java.time.OffsetDateTime time;

}//end EditAbacCommand