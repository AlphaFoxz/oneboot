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
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.apis.PsysAuthApi;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.*;
import com.github.alphafoxz.oneboot.preset_sys.service.auth.PsysAuthTokenService;
import com.github.alphafoxz.oneboot.preset_sys.service.auth.convert.PsysAuthConvert;
import com.github.alphafoxz.oneboot.preset_sys.service.auth.crud.PsysAuthDepartmentCrud;
import com.github.alphafoxz.oneboot.preset_sys.service.auth.crud.PsysAuthRoleCrud;
import com.github.alphafoxz.oneboot.preset_sys.service.auth.crud.PsysAuthUserCrud;
import com.github.alphafoxz.oneboot.preset_sys.service.framework.PageResponse;
import jakarta.annotation.Resource;
import lombok.Setter;
import org.jooq.Condition;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Setter
public class PsysAuthController implements PsysAuthApi {
    @Resource
    private PsysAuthTokenService psysAuthTokenService;
    @Resource
    private PsysAuthRoleCrud psysAuthRoleCrud;
    @Resource
    private PsysAuthUserCrud psysAuthUserCrud;
    @Resource
    private PsysAuthDepartmentCrud psysAuthDepartmentCrud;
    @Resource
    private PsysAuthConvert psysAuthConvert;

    public ResponseEntity<PsysAuthTokenResponse> login(PsysAuthLoginParam loginParam) {
        PsysAuthTokenResponse result = new PsysAuthTokenResponse();
        result.setSuccess(true);
        PsysAuthTokenInfoDto token = psysAuthTokenService.getAccessTokenAndRefreshToken(loginParam.getUsername(), loginParam.getPassword());
        result.setData(token);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<?> logout() {
        psysAuthTokenService.logout();
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<PsysAuthTokenResponse> refreshToken(PsysAuthTokenInfoDto userInfoDto) {
        PsysAuthTokenResponse result = new PsysAuthTokenResponse();
        result.setSuccess(true);
        PsysAuthTokenInfoDto token = psysAuthTokenService.refreshTokenByOld(userInfoDto);
        result.setData(token);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<PageResponse<PsysAuthRoleInfoDto>> rolePage(PsysAuthRolePageParam param) {
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
        PageResponse<PsysAuthRoleInfoDto> result = new PageResponse<>(psysAuthConvert.roleInfoDtoList(page.getContent()), page.getPageable(), page.getTotalElements());
        return ResponseEntity.ok().body(result);
    }

    @Override
    public ResponseEntity<PageResponse<PsysAuthUserInfoDto>> userPage(PsysAuthUserPageParam param) {
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
        PageResponse<PsysAuthUserInfoDto> result = new PageResponse<>(psysAuthConvert.userInfoDtoList(page.getContent()), page.getPageable(), page.getTotalElements());
        return ResponseEntity.ok().body(result);
    }

    @Override
    public ResponseEntity<PageResponse<PsysDepartmentInfoDto>> departmentPage(PsysAuthDepartmentPageParam param) {
        List<Condition> condList = CollUtil.newArrayList();
        PsysAuthDepartment table = PsysAuthDepartment.PSYS_AUTH_DEPARTMENT;
        if (StrUtil.isNotBlank(param.getName())) {
            table.DEPT_NAME.startsWith(param.getName());
        }
        if (param.getStatus() != null) {
            table.STATUS.eq(param.getStatus());
        }
        Page<PsysAuthDepartmentPo> page = psysAuthDepartmentCrud.selectPage(U.intVal(param.getPageNum(), 1), U.intVal(param.getPageSize(), 10), (ArrayUtil.toArray(condList, Condition.class)));
        PageResponse<PsysDepartmentInfoDto> result = new PageResponse<>(psysAuthConvert.departmentInfoDtoList(page.getContent()), page.getPageable(), page.getTotalElements());
        return ResponseEntity.ok().body(result);
    }
}
