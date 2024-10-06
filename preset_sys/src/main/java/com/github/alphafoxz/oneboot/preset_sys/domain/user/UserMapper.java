package com.github.alphafoxz.oneboot.preset_sys.domain.user;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.AccountVo;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.TokenVo;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.UserVo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysAccountRecord;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysTokenRecord;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysUserRecord;
import com.github.alphafoxz.oneboot.preset_sys.gen.mapper.UserVoMapper;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper extends UserVoMapper {
    public TokenVo psysTokenRecordToTokenVo(PsysTokenRecord source);

    public UserVo psysUserRecordToUserVo(PsysUserRecord source);

    public AccountVo psysAccountRecordToAccountVo(PsysAccountRecord source);
}
