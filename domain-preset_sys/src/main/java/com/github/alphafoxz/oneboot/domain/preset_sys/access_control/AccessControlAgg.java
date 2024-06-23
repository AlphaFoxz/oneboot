package com.github.alphafoxz.oneboot.domain.preset_sys.access_control;


/**
 * @author Wong
 * @version 1.0
 * @created 23-6月-2024 15:29:56
 */
public interface AccessControlAgg {

	/**
	 * 通过resourceId判断是否有访问权限
	 * 
	 * @param resourceId
	 */
	public boolean hasPermissionToAccessByResourceId(Long resourceId);

}