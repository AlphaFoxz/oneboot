package com.github.alphafoxz.oneboot.domain.preset_sys.user.vo;


/**
 * 用户状态
 * @author Wong
 * @version 1.0
 * @created 22-6月-2024 1:59:38
 */
@lombok.Getter(lombok.AccessLevel.PUBLIC)
public enum UserStatus {
	/**
	 * 启用
	 */
	ENABLED,
	/**
	 * 禁用
	 */
	DISABLED
}