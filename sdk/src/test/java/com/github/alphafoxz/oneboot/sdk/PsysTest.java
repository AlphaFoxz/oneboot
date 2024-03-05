package com.github.alphafoxz.oneboot.sdk;

import cn.hutool.core.lang.Snowflake;
import com.github.alphafoxz.oneboot.core.standard.access_control.AbacActionType;
import com.github.alphafoxz.oneboot.core.toolkit.coding.FileUtil;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysUserRecord;
import com.github.alphafoxz.oneboot.preset_sys.service.abac.PsysAbacService;
import com.github.alphafoxz.oneboot.preset_sys.service.abac.policy.PsysAbacRefuseAllBusinessPolicy;
import com.github.alphafoxz.oneboot.preset_sys.service.crud.PsysUserCrud;
import jakarta.annotation.Resource;
import org.jooq.exception.IntegrityConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.time.OffsetDateTime;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PsysTest {
    @Resource
    private PsysAbacService psysAcApiService;
    @Resource
    private Snowflake snowflake;
    @Resource
    private PsysUserCrud psysUserCrud;

    @SuppressWarnings("unchecked")
    @Test
    void test() {
        boolean b = psysAcApiService.access(1704372248082780160L, "app", "app_test", 1704389955196948480L, AbacActionType.READ, new Class[]{PsysAbacRefuseAllBusinessPolicy.class});
        Assertions.assertTrue(b);
    }

    //    @Test
    void storeKeyPair() throws NoSuchAlgorithmException {

        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(256);
        KeyPair keyPair = generator.generateKeyPair();

        FileUtil.writeBytes(keyPair.getPrivate().getEncoded(), "F:\\idea_projects\\oneboot\\preset_sys\\src\\main\\resources\\root-rsa.pri");
        FileUtil.writeBytes(keyPair.getPublic().getEncoded(), "F:\\idea_projects\\oneboot\\preset_sys\\src\\main\\resources\\root-rsa.pub");
    }

    @Test
    public void registerTest() {
        PsysUserRecord record = new PsysUserRecord();
        String password = new BCryptPasswordEncoder().encode("admin123");
        record.setId(snowflake.nextId())
                .setUsername("admin")
                .setPassword(password)
                .setNickname("系统管理员")
                .setSubjectId(snowflake.nextId())
                .setAccountId(snowflake.nextId())
                .setEnabled(true)
                .setCreateTime(OffsetDateTime.now())
                .setStatus(Integer.valueOf(0).shortValue())
                .setExpired(false);
        try {
            psysUserCrud.insert(record);
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertInstanceOf(IntegrityConstraintViolationException.class, e);
        }
    }
}
