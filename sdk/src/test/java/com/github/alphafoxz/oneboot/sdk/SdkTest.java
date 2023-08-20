package com.github.alphafoxz.oneboot.sdk;

import cn.hutool.core.lang.Snowflake;
import com.github.alphafoxz.oneboot.sdk.service.SdkGenCodeService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SdkTest {
    @Resource
    private SdkGenCodeService sdkGenCodeService;
    @Resource
    private Snowflake snowflake;

    @Test
    public void generateAllTest() {
        assert sdkGenCodeService.generateJavaRpc(snowflake.nextId()).isSuccess();
    }
}
