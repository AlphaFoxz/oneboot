package com.github.alphafoxz.oneboot.preset_sys.service.abac.mapper;

import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAbacDynamicAuthorizationPo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysAbacDynamicAuthorizationRecord;
import com.github.alphafoxz.oneboot.preset_sys.gen.restl.dtos.PsysAbacDynamicAuthDto;
import com.github.alphafoxz.oneboot.preset_sys.service.framework.CommonMapper;
import com.github.alphafoxz.oneboot.preset_sys.service.framework.Page;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(uses = CommonMapper.class)
public interface PsysAbacMapper {
    PsysAbacDynamicAuthDto toPsysAbacDynamicAuthDto(PsysAbacDynamicAuthorizationPo source);

    PsysAbacDynamicAuthorizationRecord toPsysAbacDynamicAuthorizationRecord(PsysAbacDynamicAuthDto source);

    List<PsysAbacDynamicAuthDto> toPsysAbacDynamicAuthDtoList(List<PsysAbacDynamicAuthorizationPo> source);

    default Page<PsysAbacDynamicAuthDto> toPsysAbacDynamicAuthDtoPage(org.springframework.data.domain.Page<PsysAbacDynamicAuthorizationPo> source) {
        return new Page<>(toPsysAbacDynamicAuthDtoList(source.getContent()), source.getPageable(), source.getTotalElements());
    }
}
