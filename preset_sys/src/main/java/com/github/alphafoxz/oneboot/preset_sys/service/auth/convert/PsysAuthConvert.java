package com.github.alphafoxz.oneboot.preset_sys.service.auth.convert;

import com.github.alphafoxz.oneboot.common.standard.framework.MapStructConvert;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysUserDepartmentPo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysUserPo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysUserRolePo;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysAccountDepartmentInfoDto;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysAccountRoleInfoDto;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysAccountUserInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = {LocalDateTime.class})
public interface PsysAuthConvert extends MapStructConvert {
    public static PsysAuthConvert INSTANCE = Mappers.getMapper(PsysAuthConvert.class);

    @Mapping(target = "createTime", expression = CREATE_TIME)
    @Mapping(target = "updateTime", expression = UPDATE_TIME)
    public PsysAccountRoleInfoDto roleInfoDto(PsysUserRolePo source);

    public List<PsysAccountRoleInfoDto> roleInfoDtoList(List<PsysUserRolePo> source);

    @Mapping(target = "createTime", expression = CREATE_TIME)
    public PsysAccountUserInfoDto userInfoDto(PsysUserPo source);

    public List<PsysAccountUserInfoDto> userInfoDtoList(List<PsysUserPo> source);

    public PsysAccountDepartmentInfoDto departmentInfoDto(PsysUserDepartmentPo source);

    public List<PsysAccountDepartmentInfoDto> departmentInfoDtoList(List<PsysUserDepartmentPo> source);
}
