package com.github.alphafoxz.oneboot.preset_sys.service.auth.convert;

import com.github.alphafoxz.oneboot.preset_sys.annotation.CreateTimeDtoMapping;
import com.github.alphafoxz.oneboot.preset_sys.annotation.UpdateTimeDtoMapping;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysUserDepartmentPo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysUserPo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysUserRolePo;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysAccountDepartmentInfoDto;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysAccountRoleInfoDto;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysAccountUserInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PsysAuthConvert {
    @CreateTimeDtoMapping
    @UpdateTimeDtoMapping
    public PsysAccountRoleInfoDto roleInfoDto(PsysUserRolePo source);

    public List<PsysAccountRoleInfoDto> roleInfoDtoList(List<PsysUserRolePo> source);

    @CreateTimeDtoMapping
    public PsysAccountUserInfoDto userInfoDto(PsysUserPo source);

    public List<PsysAccountUserInfoDto> userInfoDtoList(List<PsysUserPo> source);

    public PsysAccountDepartmentInfoDto departmentInfoDto(PsysUserDepartmentPo source);

    public List<PsysAccountDepartmentInfoDto> departmentInfoDtoList(List<PsysUserDepartmentPo> source);
}
