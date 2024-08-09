package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.event;


/**
 * 添加动态访问控制成功事件
 * @author Wong
 * @version 1.0
 * @created 03-7月-2024 7:16:44
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.Getter
public class AddDacSuccessdEvent {

    private Long operatorSubjectId;
    private Long resourceId;
    private java.time.OffsetDateTime time;

}//end AddDacSuccessdEvent