package com.github.alphafoxz.oneboot.preset_sys.service.access_control;

import com.github.alphafoxz.oneboot.common.ifaces.CachedCrudService;
import com.github.alphafoxz.oneboot.common.ifaces.access_control.AcActionTypeIface;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysDacAuthorization;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysDacAuthorizationPo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysDacAuthorizationRecord;
import jakarta.annotation.Resource;
import lombok.Getter;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.cache.CacheManager;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.github.alphafoxz.oneboot.preset_sys.gen.jooq.Tables.PSYS_DAC_AUTHORIZATION;

@Service
@Getter
public class PsysDacAuthorizationService implements CachedCrudService<PsysDacAuthorization, PsysDacAuthorizationPo, PsysDacAuthorizationRecord> {
    @Resource
    private DSLContext dslContext;
    @Resource
    private CacheManager cacheManager;
    private final PsysDacAuthorization table = PSYS_DAC_AUTHORIZATION;
    private final Class<PsysDacAuthorizationPo> poClass = PsysDacAuthorizationPo.class;

    /**
     * 查询所有授权
     */
    public List<PsysDacAuthorizationPo> queryAllAuth(@NonNull long subjectId, @NonNull long resourceId, @NonNull long targetSubjectId, @Nullable AcActionTypeIface actionType) {
        PsysDacAuthorization t = PSYS_DAC_AUTHORIZATION;
        Condition condition = DSL.and(
                t.OWNER_SUBJECT_ID.eq(subjectId),
                t.RESOURCE_ID.eq(resourceId),
                t.TARGET_SUBJECT_ID.eq(targetSubjectId).or(t.TARGET_SUBJECT_ID.isNull())
        );
        return queryList(1000, condition);
    }
}