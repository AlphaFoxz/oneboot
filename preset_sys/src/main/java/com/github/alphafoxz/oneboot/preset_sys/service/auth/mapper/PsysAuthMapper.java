package com.github.alphafoxz.oneboot.preset_sys.service.auth.mapper;

import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysUserDepartmentPo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysUserPo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysUserRolePo;
import com.github.alphafoxz.oneboot.preset_sys.gen.restl.dtos.PsysAccountDepartmentInfoDto;
import com.github.alphafoxz.oneboot.preset_sys.gen.restl.dtos.PsysAccountRoleInfoDto;
import com.github.alphafoxz.oneboot.preset_sys.gen.restl.dtos.PsysAccountUserInfoDto;
import com.github.alphafoxz.oneboot.preset_sys.service.framework.CommonMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = CommonMapper.class)
public interface PsysAuthMapper {
    public PsysAccountRoleInfoDto roleInfoDto(PsysUserRolePo source);

    public List<PsysAccountRoleInfoDto> roleInfoDtoList(List<PsysUserRolePo> source);

    public PsysAccountUserInfoDto userInfoDto(PsysUserPo source);

    public List<PsysAccountUserInfoDto> userInfoDtoList(List<PsysUserPo> source);

    public PsysAccountDepartmentInfoDto departmentInfoDto(PsysUserDepartmentPo source);

    public List<PsysAccountDepartmentInfoDto> departmentInfoDtoList(List<PsysUserDepartmentPo> source);
}
