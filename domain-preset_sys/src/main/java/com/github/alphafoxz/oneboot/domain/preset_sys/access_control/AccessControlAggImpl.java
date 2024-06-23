package com.github.alphafoxz.oneboot.domain.preset_sys.access_control;


import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.AbacAttr;
import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.DacRecord;

/**
 * 访问控制聚合
 * @author Wong
 * @version 1.0
 * @created 23-6月-2024 23:38:47
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter(lombok.AccessLevel.PACKAGE)
@lombok.NoArgsConstructor(access = lombok.AccessLevel.PACKAGE)
@lombok.Getter(lombok.AccessLevel.PUBLIC)
public class AccessControlAggImpl implements AccessControlAgg {

	private java.util.Set<AbacAttr> abacAttrSet;
	private java.util.Set<DacRecord> dacSet;
	/**
	 * 用户主键
	 */
	private Long subjectId;

	/**
	 * 通过resourceId判断是否有访问权限
	 * 
	 * @param resourceId    资源id
	 */
	public boolean hasPermissionToAccessByResourceId(Long resourceId){
		return false;
	}
}//end AccessControlAggImpl