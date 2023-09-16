package com.github.alphafoxz.oneboot.preset_sys.service.access_control;

import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAbacResourceProtection;
import jakarta.annotation.Resource;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import static com.github.alphafoxz.oneboot.preset_sys.gen.jooq.Tables.PSYS_ABAC_RESOURCE_PROTECTION;

/**
 * 访问控制-资源处理
 */
public class PsysAcResourceService {
    @Resource
    private DSLContext dslContext;

    /**
     * 判断资源是否被保护
     */
    @Nullable
    public PsysAbacResourceProtection queryProtectionInfo(@NonNull String schemaName, @NonNull String tableName) {
        //XXX 此处考虑使用内存实现而不是数据库交互，因为调用会非常频繁
        Record record = dslContext
                .select(
                        PSYS_ABAC_RESOURCE_PROTECTION.fields()
                )
                .from(PSYS_ABAC_RESOURCE_PROTECTION)
                .where(
                        DSL.and(
                                PSYS_ABAC_RESOURCE_PROTECTION.SCHEMA_NAME.eq(schemaName.toLowerCase()),
                                PSYS_ABAC_RESOURCE_PROTECTION.TABLE_NAME.eq(tableName.toLowerCase())
                        )
                )
                .fetchOne();
        return record == null ? null : record.into(PsysAbacResourceProtection.class);
    }
}
