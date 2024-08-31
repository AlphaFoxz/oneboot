package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.entity;

import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.DacType;

/**
 * @author Wong
 * @version 1.0
 * @created 03-7月-2024 7:16:44
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.Getter
public class DacRecord {
    private DacType dacType;
    private Long resourceId;
}