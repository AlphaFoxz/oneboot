package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo;


/**
 * 资源操作类型
 * @author Wong
 * @version 1.0
 * @created 22-6月-2024 1:59:37
 */
@lombok.Getter(lombok.AccessLevel.PUBLIC)
public enum ResourceActionType {
	/**
	 * 创建
	 */
	CREATE,
	/**
	 * 读取
	 */
	READ,
	/**
	 * 更新
	 */
	UPDATE,
	/**
	 * 逻辑删除
	 */
	LOGIC_DELETE,
	/**
	 * 删除
	 */
	DELETE
}