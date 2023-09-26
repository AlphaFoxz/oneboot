package com.github.alphafoxz.oneboot.sdk;

import com.github.alphafoxz.oneboot.common.interfaces.access_control.AbacActionType;
import com.github.alphafoxz.oneboot.common.toolkit.coding.FileUtil;
import com.github.alphafoxz.oneboot.preset_sys.service.abac.PsysAbacApiService;
import com.github.alphafoxz.oneboot.preset_sys.service.abac.policy.PsysAbacRefuseAllBusinessPolicy;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PsysTest {
    @Resource
    private PsysAbacApiService psysAcApiService;

    @SuppressWarnings("unchecked")
    @Test
    void test() {
        boolean b = psysAcApiService.access(1704372248082780160L, "app", "app_test", 1704389955196948480L, AbacActionType.READ, new Class[]{PsysAbacRefuseAllBusinessPolicy.class});
        System.err.println(b);
    }

    @Test
    void storeKeyPair() throws NoSuchAlgorithmException {

        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(256);
        KeyPair keyPair = generator.generateKeyPair();

        FileUtil.writeBytes(keyPair.getPrivate().getEncoded(), "F:\\idea_projects\\oneboot\\preset_sys\\src\\main\\resources\\root-rsa.pri");
        FileUtil.writeBytes(keyPair.getPublic().getEncoded(), "F:\\idea_projects\\oneboot\\preset_sys\\src\\main\\resources\\root-rsa.pub");
    }
}
