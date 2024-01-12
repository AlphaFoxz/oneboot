package com.github.alphafoxz.oneboot.preset_sys.service.security;

import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.Tables;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysUser;

interface C {
    PsysUser PSYS_USER = Tables.PSYS_USER;
}