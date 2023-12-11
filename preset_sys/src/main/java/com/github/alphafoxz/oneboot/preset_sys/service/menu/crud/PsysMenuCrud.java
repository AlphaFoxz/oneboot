package com.github.alphafoxz.oneboot.preset_sys.service.menu.crud;

import com.github.alphafoxz.oneboot.common.interfaces.framework.impl.AbstractCachedCrudService;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysMenu;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysMenuPo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysMenuRecord;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.springframework.cache.CacheManager;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import static com.github.alphafoxz.oneboot.preset_sys.gen.jooq.Tables.PSYS_MENU;

/**
 * PsysMenu表增删改查service
 */
@Slf4j
@Getter
@Service
public class PsysMenuCrud extends AbstractCachedCrudService<PsysMenu, PsysMenuPo, PsysMenuRecord> {
    @Resource
    private DSLContext dslContext;
    @Resource
    private CacheManager cacheManager;
    private final PsysMenu table = PSYS_MENU;
    private final Class<PsysMenuPo> poClass = PsysMenuPo.class;

    @Override
    @NonNull
    public Logger getLogger() {
        return log;
    }
}
