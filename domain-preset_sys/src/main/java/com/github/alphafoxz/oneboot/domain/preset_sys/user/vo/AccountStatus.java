package com.github.alphafoxz.oneboot.domain.preset_sys.user.vo;


/**
 * 账户状态
 * @author Wong
 * @version 1.0
 * @created 22-6月-2024 1:59:36
 */
@lombok.Getter(lombok.AccessLevel.PUBLIC)
public enum AccountStatus {
	/**
	 * 启用
	 */
	ENABLED,
	/**
	 * 禁用
	 */
	DISABLED
}