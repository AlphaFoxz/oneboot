package com.github.alphafoxz.oneboot.domain.preset_sys.access_control;


/**
 * @author Wong
 * @version 1.0
 * @created 03-7月-2024 7:16:43
 */
public interface AccessControlAgg {

    /**
     * 通过resourceId判断是否有访问权限
     * 
     * @param resourceId    资源id
     */
    public boolean hasPermissionToAccessByResourceId(Long resourceId);

}