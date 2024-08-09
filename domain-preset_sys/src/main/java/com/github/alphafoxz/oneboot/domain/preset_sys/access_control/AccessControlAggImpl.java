package com.github.alphafoxz.oneboot.domain.preset_sys.access_control;


import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.AbacAttr;
import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.entity.DacRecord;

/**
 * 访问控制聚合
 * @author Wong
 * @version 1.0
 * @created 03-7月-2024 7:16:43
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.Getter
public class AccessControlAggImpl implements AccessControlAgg {

    private java.util.Set<AbacAttr> abacAttrSet;
    private java.util.Set<DacRecord> dacSet;
    private Long resourceId;
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