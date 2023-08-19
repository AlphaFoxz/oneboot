package com.github.alphafoxz.oneboot.sdk;

import com.github.alphafoxz.oneboot.sdk.service.SdkThriftService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SdkTest {
    @Resource
    private SdkThriftService sdkThriftService;

    @Test
    public void generateAllTest() {
        assert sdkThriftService.generateAll().isSuccess();
    }
}
