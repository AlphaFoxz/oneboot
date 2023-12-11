package com.github.alphafoxz.oneboot.preset_sys.controller;

import com.github.alphafoxz.oneboot.common.toolkit.coding.CollUtil;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAuthRolePo;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.apis.PsysAuthApi;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysAuthLoginParam;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysAuthRoleInfoDto;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysAuthTokenInfoDto;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysAuthTokenResponse;
import com.github.alphafoxz.oneboot.preset_sys.service.auth.PsysAuthTokenService;
import com.github.alphafoxz.oneboot.preset_sys.service.auth.crud.PsysAuthRoleCrud;
import com.github.alphafoxz.oneboot.preset_sys.service.auth.crud.PsysAuthUserCrud;
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
    public ResponseEntity<PsysAuthTokenResponse> refresh(PsysAuthTokenInfoDto userInfoDto) {
        PsysAuthTokenResponse result = new PsysAuthTokenResponse();
        result.setSuccess(true);
        PsysAuthTokenInfoDto token = psysAuthTokenService.refreshTokenByOld(userInfoDto);
        result.setData(token);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Page<PsysAuthRoleInfoDto>> roleList(Integer pageNum, Integer pageSize) {
        Page<PsysAuthRolePo> psysAuthRolePos = psysAuthRoleCrud.selectPage(pageNum, pageSize);
        return null;
    }

    @Override
    public ResponseEntity<Page<PsysAuthRoleInfoDto>> userList(Integer pageNum, Integer pageSize) {
        List<Condition> conditionList = CollUtil.newArrayList();
        psysAuthUserCrud.selectPage(pageNum, pageSize, (Condition[]) conditionList.toArray());
        return null;
    }
}
