package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo;


/**
 * 动态访问控制类型
 *
 * @author Wong
 * @version 1.0
 * @created 03-7月-2024 7:16:44
 */
@lombok.Getter
public enum DacType {
    /**
     * 主动
     */
    INITIATIVE,
    /**
     * 被动
     */
    PASSIVE
}