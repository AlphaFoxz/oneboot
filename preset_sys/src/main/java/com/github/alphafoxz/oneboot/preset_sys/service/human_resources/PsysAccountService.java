package com.github.alphafoxz.oneboot.preset_sys.service.human_resources;

import com.github.alphafoxz.oneboot.common.toolkit.Pair;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysHrUserPo;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

/**
 * 账号service
 */
@Service
public class PsysAccountService {
    @Nullable
    public Pair<PsysHrUserPo, Object> getAccountInfoByUsername(@Nullable String username) {
        // TODO
        return null;
    }

}
