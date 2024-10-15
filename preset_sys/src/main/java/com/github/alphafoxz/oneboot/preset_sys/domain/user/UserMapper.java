package com.github.alphafoxz.oneboot.preset_sys.domain.user;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.AccountVo;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.TokenVo;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.UserVo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAccount;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysToken;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysUser;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysAccountRecord;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysTokenRecord;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysUserRecord;
import com.github.alphafoxz.oneboot.preset_sys.gen.mapper.UserVoMapper;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper extends UserVoMapper {
    default TokenVo psysTokenRecordToTokenVo(PsysTokenRecord source) {
        return psysTokenToTokenVo(psysTokenRecordToPsysTokenPo(source));
    }

    default PsysToken psysTokenRecordToPsysTokenPo(PsysTokenRecord source) {
        return source.into(PsysToken.class);
    }

    TokenVo psysTokenToTokenVo(PsysToken source);

    default UserVo psysUserRecordToUserVo(PsysUserRecord source) {
        return psysUserToUserVo(psysUserRecordToPsysUserPo(source));
    }

    default PsysUser psysUserRecordToPsysUserPo(PsysUserRecord source) {
        return source.into(PsysUser.class);
    }

    UserVo psysUserToUserVo(PsysUser source);

    default AccountVo psysAccountRecordToAccountVo(PsysAccountRecord source) {
        return psysAccountToAccountVo(psysAccountRecordToPsysAccountPo(source));
    }

    default PsysAccount psysAccountRecordToPsysAccountPo(PsysAccountRecord source) {
        return source.into(PsysAccount.class);
    }

    AccountVo psysAccountToAccountVo(PsysAccount source);
}
