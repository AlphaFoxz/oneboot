package com.github.alphafoxz.oneboot.domain.preset_sys.access_control;


import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.entity.DacRecord;
import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.AbacAttrVo;
import lombok.AllArgsConstructor;

/**
 * 访问控制聚合
 */
@AllArgsConstructor
public class AccessControlAggImpl implements AccessControlAgg {
    private java.util.Set<AbacAttrVo> abacAttrSet;
    private java.util.Set<DacRecord> dacSet;
    private Long resourceId;
    /**
     * 用户主键
     */
    private Long subjectId;

    /**
     * 通过resourceId判断是否有访问权限
     *
     * @param resourceId 资源id
     */
    public boolean hasPermissionToAccessByResourceId(Long resourceId) {
        return false;
    }
}//end AccessControlAggImpl