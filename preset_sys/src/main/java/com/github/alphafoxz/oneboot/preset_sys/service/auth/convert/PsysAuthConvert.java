package com.github.alphafoxz.oneboot.preset_sys.service.auth.convert;

import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysUserDepartmentPo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysUserPo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysUserRolePo;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysAccountDepartmentInfoDto;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysAccountRoleInfoDto;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysAccountUserInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PsysAuthConvert {
    public static PsysAuthConvert INSTANCE = Mappers.getMapper(PsysAuthConvert.class);

    public PsysAccountRoleInfoDto roleInfoDto(PsysUserRolePo psysUserRolePo);

    public List<PsysAccountRoleInfoDto> roleInfoDtoList(List<PsysUserRolePo> psysUserRolePo);

    public PsysAccountUserInfoDto userInfoDto(PsysUserPo psysAuthUserPo);

    public List<PsysAccountUserInfoDto> userInfoDtoList(List<PsysUserPo> psysAuthUserPo);

    public PsysAccountDepartmentInfoDto departmentInfoDto(PsysUserDepartmentPo psysAuthDepartmentPo);

    public List<PsysAccountDepartmentInfoDto> departmentInfoDtoList(List<PsysUserDepartmentPo> psysAuthDepartmentPo);
}
