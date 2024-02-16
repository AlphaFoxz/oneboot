/**
 * auth包提供系统权限相关的实现
 */
package com.github.alphafoxz.oneboot.preset_sys.service.auth;

import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.Tables;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysUser;

interface Const {
    PsysUser PSYS_USER = Tables.PSYS_USER;
}
