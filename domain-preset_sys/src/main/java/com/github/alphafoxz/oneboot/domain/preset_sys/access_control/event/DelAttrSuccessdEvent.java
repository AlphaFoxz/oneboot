package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.event;

import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.AbacAttr;

/**
 * 删除属性成功事件
 * @author Wong
 * @version 1.0
 * @created 03-7月-2024 7:16:44
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.Getter
public class DelAttrSuccessdEvent {

    private AbacAttr abacAttr;
    private Long operatorSubjectId;
    private Long subjectId;
    private java.time.OffsetDateTime time;

}//end DelAttrSuccessdEvent