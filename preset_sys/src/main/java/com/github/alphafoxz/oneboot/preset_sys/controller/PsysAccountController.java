package com.github.alphafoxz.oneboot.preset_sys.controller;

import com.github.alphafoxz.oneboot.common.toolkit.coding.ArrayUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.CollUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.StrUtil;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAuthDepartment;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAuthRole;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAuthUser;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAuthDepartmentPo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAuthRolePo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAuthUserPo;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.apis.PsysAccountApi;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.*;
import com.github.alphafoxz.oneboot.preset_sys.service.auth.convert.PsysAccountConvert;
import com.github.alphafoxz.oneboot.preset_sys.service.auth.crud.PsysAuthDepartmentCrud;
import com.github.alphafoxz.oneboot.preset_sys.service.auth.crud.PsysAuthRoleCrud;
import com.github.alphafoxz.oneboot.preset_sys.service.auth.crud.PsysAuthUserCrud;
import com.github.alphafoxz.oneboot.preset_sys.service.framework.PageResponse;
import jakarta.annotation.Resource;
import org.jooq.Condition;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PsysAccountController implements PsysAccountApi {
    @Resource
    private PsysAuthRoleCrud psysAuthRoleCrud;
    @Resource
    private PsysAuthUserCrud psysAuthUserCrud;
    @Resource
    private PsysAuthDepartmentCrud psysAuthDepartmentCrud;
    @Resource
    private PsysAccountConvert psysAccountConvert;

    @Override
    public ResponseEntity<PageResponse<PsysAccountRoleInfoDto>> rolePage(PsysAccountRolePageParam param) {
        PsysAuthRole table = PsysAuthRole.PSYS_AUTH_ROLE;
        List<Condition> condList = CollUtil.newArrayList();
        if (StrUtil.isNotBlank(param.getCode())) {
            condList.add(table.ROLE_CODE.eq(param.getCode()));
        }
        if (StrUtil.isNotBlank(param.getName())) {
            condList.add(table.ROLE_NAME.startsWith(param.getName()));
        }
        if (param.getStatus() != null) {
            condList.add(table.STATUS.eq(param.getStatus()));
        }
        Page<PsysAuthRolePo> page = psysAuthRoleCrud.selectPage(U.intVal(param.getPageNum(), 1), U.intVal(param.getPageSize(), 10), ArrayUtil.toArray(condList, Condition.class));
        PageResponse<PsysAccountRoleInfoDto> result = new PageResponse<>(psysAccountConvert.roleInfoDtoList(page.getContent()), page.getPageable(), page.getTotalElements());
        return ResponseEntity.ok().body(result);
    }

    @Override
    public ResponseEntity<PageResponse<PsysAccountUserInfoDto>> userPage(PsysAccountUserPageParam param) {
        PsysAuthUser table = PsysAuthUser.PSYS_AUTH_USER;
        List<Condition> condList = CollUtil.newArrayList();
        if (param.getDeptId() != null) {
            condList.add(table.DEPARTMENT_ID.eq(param.getDeptId()));
        }
        if (StrUtil.isNotBlank(param.getUsername())) {
            condList.add(table.USERNAME.startsWith(param.getUsername()));
        }
        if (StrUtil.isNotBlank(param.getPhone())) {
            condList.add(table.PHONE.startsWith(param.getPhone()));
        }
        Page<PsysAuthUserPo> page = psysAuthUserCrud.selectPage(U.intVal(param.getPageNum(), 1), U.intVal(param.getPageSize(), 10), ArrayUtil.toArray(condList, Condition.class));
        PageResponse<PsysAccountUserInfoDto> result = new PageResponse<>(psysAccountConvert.userInfoDtoList(page.getContent()), page.getPageable(), page.getTotalElements());
        return ResponseEntity.ok().body(result);
    }

    @Override
    public ResponseEntity<PageResponse<PsysAccountDepartmentInfoDto>> departmentPage(PsysAccountDepartmentPageParam param) {
        List<Condition> condList = CollUtil.newArrayList();
        PsysAuthDepartment table = PsysAuthDepartment.PSYS_AUTH_DEPARTMENT;
        if (StrUtil.isNotBlank(param.getName())) {
            table.DEPT_NAME.startsWith(param.getName());
        }
        if (param.getStatus() != null) {
            table.STATUS.eq(param.getStatus());
        }
        Page<PsysAuthDepartmentPo> page = psysAuthDepartmentCrud.selectPage(U.intVal(param.getPageNum(), 1), U.intVal(param.getPageSize(), 10), (ArrayUtil.toArray(condList, Condition.class)));
        PageResponse<PsysAccountDepartmentInfoDto> result = new PageResponse<>(psysAccountConvert.departmentInfoDtoList(page.getContent()), page.getPageable(), page.getTotalElements());
        return ResponseEntity.ok().body(result);
    }
}
