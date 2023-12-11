package com.github.alphafoxz.oneboot.preset_sys.service.abac;

import com.github.alphafoxz.oneboot.common.interfaces.access_control.AbacAttr;
import com.github.alphafoxz.oneboot.common.interfaces.access_control.AbacDynamicAuthApi;
import com.github.alphafoxz.oneboot.common.interfaces.access_control.impl.AbacAttrImpl;
import com.github.alphafoxz.oneboot.common.toolkit.coding.JSONUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.MapUtil;
import com.github.alphafoxz.oneboot.common.toolkit.container.tuple.Tuple2;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAbacDynamicAuthorization;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAbacDynamicAuthorizationPo;
import com.github.alphafoxz.oneboot.preset_sys.service.abac.crud.PsysAbacDynamicAuthorizationCrud;
import jakarta.annotation.Resource;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.github.alphafoxz.oneboot.preset_sys.gen.jooq.Tables.PSYS_ABAC_DYNAMIC_AUTHORIZATION;

/**
 * 动态访问控制api
 */
@Service
public class PsysAbacDynamicAuthService implements AbacDynamicAuthApi {
    @Resource
    private PsysAbacDynamicAuthorizationCrud psysAbacDynamicAuthorizationCrud;

    /**
     * 查询授权
     *
     * @return 资源拥有者id, 授权属性
     */
    @Override
    @Nullable
    public Tuple2<Long, Map<String, AbacAttr>> queryAuthorization(long ownerSubjectId, long resourceId, @Nullable Long targetSubjectId, @NonNull String actionType) {
        PsysAbacDynamicAuthorization t = PSYS_ABAC_DYNAMIC_AUTHORIZATION;
        List<PsysAbacDynamicAuthorizationPo> list = psysAbacDynamicAuthorizationCrud.selectList(1000,
                t.OWNER_SUBJECT_ID.eq(ownerSubjectId),
                t.RESOURCE_ID.eq(resourceId),
                t.TARGET_SUBJECT_ID.eq(targetSubjectId).or(t.TARGET_SUBJECT_ID.isNull())
        );
        Long owner = null;
        Map<String, AbacAttr> result = MapUtil.newHashMap();
        for (var psysDacAuthorizationPo : list) {
            owner = psysDacAuthorizationPo.ownerSubjectId();
            for (var attr : JSONUtil.parseArray(psysDacAuthorizationPo.subjectAttrSet())) {
                AbacAttr abacAttr = AbacAttrImpl.of(attr.toString());
                result.put(abacAttr.getName(), abacAttr);
            }
        }
        if (owner == null) {
            return null;
        }
        return Tuple2.of(owner, result);
    }
}
