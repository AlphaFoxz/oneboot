package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo;


/**
 * 动态访问控制类型
 * @author Wong
 * @version 1.0
 * @created 22-6月-2024 1:59:36
 */
@lombok.Getter(lombok.AccessLevel.PUBLIC)
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