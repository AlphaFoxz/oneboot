package com.github.alphafoxz.oneboot.sdk;

import cn.hutool.core.lang.Snowflake;
import com.github.alphafoxz.oneboot.common.standard.access_control.AbacActionType;
import com.github.alphafoxz.oneboot.common.toolkit.coding.FileUtil;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysAuthUserRecord;
import com.github.alphafoxz.oneboot.preset_sys.service.abac.PsysAbacService;
import com.github.alphafoxz.oneboot.preset_sys.service.abac.policy.PsysAbacRefuseAllBusinessPolicy;
import com.github.alphafoxz.oneboot.preset_sys.service.auth.crud.PsysAuthUserCrud;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PsysTest {
    @Resource
    private PsysAbacService psysAcApiService;
    @Resource
    private Snowflake snowflake;
    @Resource
    private PsysAuthUserCrud psysAuthUserCrud;

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

    @Test
    public void registerTest() {
        PsysAuthUserRecord record = new PsysAuthUserRecord();
        String password = new BCryptPasswordEncoder().encode("admin123");
        record.setId(snowflake.nextId());
        record.setUsername("admin");
        record.setPassword(password);
        record.setNickname("系统管理员");
        record.setSubjectId(snowflake.nextId());
        record.setAccountId(snowflake.nextId());
        record.setEnabled(true);
        record.setExpired(false);
        psysAuthUserCrud.insert(record);
    }
}
