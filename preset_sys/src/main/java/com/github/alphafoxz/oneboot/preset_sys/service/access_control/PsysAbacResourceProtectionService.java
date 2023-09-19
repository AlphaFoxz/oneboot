package com.github.alphafoxz.oneboot.preset_sys.service.access_control;

import com.github.alphafoxz.oneboot.common.ifaces.CachedCrudService;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAbacResourceProtection;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAbacResourceProtectionPo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysAbacResourceProtectionRecord;
import jakarta.annotation.Resource;
import lombok.Getter;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.springframework.cache.CacheManager;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import static com.github.alphafoxz.oneboot.preset_sys.gen.jooq.Tables.PSYS_ABAC_RESOURCE_PROTECTION;

@Service
@Getter
public class PsysAbacResourceProtectionService implements CachedCrudService<PsysAbacResourceProtection, PsysAbacResourceProtectionPo, PsysAbacResourceProtectionRecord> {
    @Resource
    private DSLContext dslContext;
    @Resource
    private CacheManager cacheManager;
    private final PsysAbacResourceProtection table = PSYS_ABAC_RESOURCE_PROTECTION;
    private final Class<PsysAbacResourceProtectionPo> poClass = PsysAbacResourceProtectionPo.class;

    @Nullable
    public PsysAbacResourceProtectionPo queryProtectionInfo(@NonNull String schemaName, @NonNull String tableName) {
        Record record = dslContext
                .select(
                        PSYS_ABAC_RESOURCE_PROTECTION.ID
                )
                .from(PSYS_ABAC_RESOURCE_PROTECTION)
                .where(
                        DSL.and(
                                PSYS_ABAC_RESOURCE_PROTECTION.SCHEMA_NAME.eq(schemaName.toLowerCase()),
                                PSYS_ABAC_RESOURCE_PROTECTION.TABLE_NAME.eq(tableName.toLowerCase())
                        )
                )
                .fetchOne();
        return queryOne(record);
    }
}