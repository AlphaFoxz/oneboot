package com.github.alphafoxz.oneboot.preset_sys.service.crud;

import com.github.alphafoxz.oneboot.preset_sys.service.crud.impl.AbstractCachedCrudService;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysUserDepartment;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysUserDepartmentPo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysUserDepartmentRecord;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.springframework.cache.CacheManager;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import static com.github.alphafoxz.oneboot.preset_sys.gen.jooq.Tables.PSYS_USER_DEPARTMENT;

/**
 * PsysAuthDepartment表增删改查service
 */
@Slf4j
@Getter
@Service
public class PsysUserDepartmentCrud extends AbstractCachedCrudService<PsysUserDepartment, PsysUserDepartmentPo, PsysUserDepartmentRecord> {
    @Resource
    private DSLContext dslContext;
    @Resource
    private CacheManager cacheManager;
    private final PsysUserDepartment table = PSYS_USER_DEPARTMENT;
    private final Class<PsysUserDepartmentPo> poClass = PsysUserDepartmentPo.class;

    @Override
    @NonNull
    public Logger getLogger() {
        return log;
    }
}
