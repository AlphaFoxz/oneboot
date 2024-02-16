/**
 * Spring Security相关实现
 */
package com.github.alphafoxz.oneboot.preset_sys.service.security;

import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.Tables;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysUser;

interface Const {
    PsysUser PSYS_USER = Tables.PSYS_USER;
}