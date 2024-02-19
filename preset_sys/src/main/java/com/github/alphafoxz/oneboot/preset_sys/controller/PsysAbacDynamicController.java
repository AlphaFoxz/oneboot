package com.github.alphafoxz.oneboot.preset_sys.controller;

import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.Tables;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAbacDynamicAuthorization;
import com.github.alphafoxz.oneboot.preset_sys.gen.restl.apis.PsysAbacDynamicApi;
import com.github.alphafoxz.oneboot.preset_sys.gen.restl.dtos.PsysAbacDynamicAuthDto;
import com.github.alphafoxz.oneboot.preset_sys.service.abac.mapper.PsysAbacMapper;
import com.github.alphafoxz.oneboot.preset_sys.service.crud.PsysAbacDynamicAuthorizationCrud;
import com.github.alphafoxz.oneboot.preset_sys.service.framework.Page;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;

public class PsysAbacDynamicController implements PsysAbacDynamicApi {
    final private PsysAbacDynamicAuthorization table = Tables.PSYS_ABAC_DYNAMIC_AUTHORIZATION;
    @Resource
    private PsysAbacDynamicAuthorizationCrud psysAbacDynamicAuthorizationCrud;
    @Resource
    private PsysAbacMapper psysAbacMapper;

    @Override
    public ResponseEntity<Page<PsysAbacDynamicAuthDto>> query(Integer pageNum, Integer pageSize) {
        pageNum = U.intVal(pageNum, 1);
        pageSize = U.intVal(pageSize, 10);
        return ResponseEntity.ok(psysAbacMapper.toPsysAbacDynamicAuthDtoPage(psysAbacDynamicAuthorizationCrud.selectPage(pageNum, pageSize)));
    }

    @Override
    public ResponseEntity<Boolean> save(PsysAbacDynamicAuthDto dto) {
        if (dto.getId() != null) {
            psysAbacDynamicAuthorizationCrud.update(psysAbacMapper.toPsysAbacDynamicAuthorizationRecord(dto));
        } else {
            psysAbacDynamicAuthorizationCrud.insert(psysAbacMapper.toPsysAbacDynamicAuthorizationRecord(dto));
        }
        return ResponseEntity.ok().body(true);
    }

    @Override
    public ResponseEntity<Boolean> delete(String id) {
        psysAbacDynamicAuthorizationCrud.deleteById(U.longVal(id));
        return ResponseEntity.ok(true);
    }
}
