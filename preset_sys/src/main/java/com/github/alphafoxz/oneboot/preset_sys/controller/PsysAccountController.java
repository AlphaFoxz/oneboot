package com.github.alphafoxz.oneboot.preset_sys.controller;

import com.github.alphafoxz.oneboot.common.toolkit.coding.ArrayUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.CollUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.StrUtil;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysUser;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysUserDepartment;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysUserRole;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysUserDepartmentPo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysUserPo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysUserRolePo;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.apis.PsysAccountApi;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.*;
import com.github.alphafoxz.oneboot.preset_sys.service.auth.convert.PsysAuthConvert;
import com.github.alphafoxz.oneboot.preset_sys.service.crud.PsysUserCrud;
import com.github.alphafoxz.oneboot.preset_sys.service.crud.PsysUserDepartmentCrud;
import com.github.alphafoxz.oneboot.preset_sys.service.crud.PsysUserRoleCrud;
import com.github.alphafoxz.oneboot.preset_sys.service.framework.Page;
import jakarta.annotation.Resource;
import org.jooq.Condition;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PsysAccountController implements PsysAccountApi {
    @Resource
    private PsysUserRoleCrud psysUserRoleCrud;
    @Resource
    private PsysUserCrud psysUserCrud;
    @Resource
    private PsysUserDepartmentCrud psysUserDepartmentCrud;
    @Resource
    private PsysAuthConvert psysAuthConvert;

    @Override
    public ResponseEntity<Page<PsysAccountRoleInfoDto>> rolePage(PsysAccountRolePageParam param) {
        PsysUserRole table = C.PSYS_USER_ROLE;
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
        org.springframework.data.domain.Page<PsysUserRolePo> page = psysUserRoleCrud.selectPage(U.intVal(param.getPageNum(), 1), U.intVal(param.getPageSize(), 10), ArrayUtil.toArray(condList, Condition.class));
        Page<PsysAccountRoleInfoDto> result = new Page<>(psysAuthConvert.roleInfoDtoList(page.getContent()), page.getPageable(), page.getTotalElements());
        return ResponseEntity.ok().body(result);
    }

    @Override
    public ResponseEntity<Page<PsysAccountUserInfoDto>> userPage(PsysAccountUserPageParam param) {
        PsysUser table = C.PSYS_USER;
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
        org.springframework.data.domain.Page<PsysUserPo> page = psysUserCrud.selectPage(U.intVal(param.getPageNum(), 1), U.intVal(param.getPageSize(), 10), ArrayUtil.toArray(condList, Condition.class));
        Page<PsysAccountUserInfoDto> result = new Page<>(psysAuthConvert.userInfoDtoList(page.getContent()), page.getPageable(), page.getTotalElements());
        return ResponseEntity.ok().body(result);
    }

    @Override
    public ResponseEntity<Page<PsysAccountDepartmentInfoDto>> departmentPage(PsysAccountDepartmentPageParam param) {
        List<Condition> condList = CollUtil.newArrayList();
        PsysUserDepartment table = C.PSYS_USER_DEPARTMENT;
        if (StrUtil.isNotBlank(param.getName())) {
            table.DEPT_NAME.startsWith(param.getName());
        }
        if (param.getStatus() != null) {
            table.STATUS.eq(param.getStatus());
        }
        org.springframework.data.domain.Page<PsysUserDepartmentPo> page = psysUserDepartmentCrud.selectPage(U.intVal(param.getPageNum(), 1), U.intVal(param.getPageSize(), 10), (ArrayUtil.toArray(condList, Condition.class)));
        Page<PsysAccountDepartmentInfoDto> result = new Page<>(psysAuthConvert.departmentInfoDtoList(page.getContent()), page.getPageable(), page.getTotalElements());
        return ResponseEntity.ok().body(result);
    }
}
