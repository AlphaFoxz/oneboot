package com.github.alphafoxz.oneboot.sdk;

import com.github.alphafoxz.oneboot.common.interfaces.access_control.AbacActionType;
import com.github.alphafoxz.oneboot.preset_sys.service.access_control.PsysAcApiService;
import com.github.alphafoxz.oneboot.preset_sys.service.access_control.policy.PsysAbacRefuseAllBusinessPolicy;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PsysTest {
    @Resource
    private PsysAcApiService psysAcApiService;

    @SuppressWarnings("unchecked")
    @Test
    void test() {
        boolean b = psysAcApiService.access(1704372248082780160L, "app", "app_test", 1704389955196948480L, AbacActionType.READ, new Class[]{PsysAbacRefuseAllBusinessPolicy.class});
        System.err.println(b);
    }
}
