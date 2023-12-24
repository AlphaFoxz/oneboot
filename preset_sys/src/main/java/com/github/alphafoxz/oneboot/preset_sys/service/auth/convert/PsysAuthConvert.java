package com.github.alphafoxz.oneboot.preset_sys.service.auth.convert;

import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAuthDepartmentPo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAuthRolePo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAuthUserPo;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysAuthRoleInfoDto;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysAuthUserInfoDto;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysDepartmentInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PsysAuthConvert {
    public static PsysAuthConvert INSTANCE = Mappers.getMapper(PsysAuthConvert.class);

    public PsysAuthRoleInfoDto roleInfoDto(PsysAuthRolePo psysAuthUserPo);

    public List<PsysAuthRoleInfoDto> roleInfoDtoList(List<PsysAuthRolePo> psysAuthUserPo);

    public PsysAuthUserInfoDto userInfoDto(PsysAuthUserPo psysAuthUserPo);

    public List<PsysAuthUserInfoDto> userInfoDtoList(List<PsysAuthUserPo> psysAuthUserPo);

    public PsysDepartmentInfoDto departmentInfoDto(PsysAuthDepartmentPo psysAuthDepartmentPo);

    public List<PsysDepartmentInfoDto> departmentInfoDtoList(List<PsysAuthDepartmentPo> psysAuthDepartmentPo);
}
