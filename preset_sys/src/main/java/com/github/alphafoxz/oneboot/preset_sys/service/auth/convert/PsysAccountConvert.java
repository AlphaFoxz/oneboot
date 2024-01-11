package com.github.alphafoxz.oneboot.preset_sys.service.auth.convert;

import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAuthDepartmentPo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAuthRolePo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAuthUserPo;
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
public interface PsysAccountConvert {
    public static PsysAccountConvert INSTANCE = Mappers.getMapper(PsysAccountConvert.class);

    public PsysAccountRoleInfoDto roleInfoDto(PsysAuthRolePo psysAuthUserPo);

    public List<PsysAccountRoleInfoDto> roleInfoDtoList(List<PsysAuthRolePo> psysAuthUserPo);

    public PsysAccountUserInfoDto userInfoDto(PsysAuthUserPo psysAuthUserPo);

    public List<PsysAccountUserInfoDto> userInfoDtoList(List<PsysAuthUserPo> psysAuthUserPo);

    public PsysAccountDepartmentInfoDto departmentInfoDto(PsysAuthDepartmentPo psysAuthDepartmentPo);

    public List<PsysAccountDepartmentInfoDto> departmentInfoDtoList(List<PsysAuthDepartmentPo> psysAuthDepartmentPo);
}
