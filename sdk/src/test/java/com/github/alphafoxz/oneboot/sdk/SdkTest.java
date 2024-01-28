package com.github.alphafoxz.oneboot.sdk;

import cn.hutool.core.lang.Snowflake;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SdkTest {
    @Resource
    private Snowflake snowflake;

    @Test
    public void nextId() {
        System.err.println(snowflake.nextId());
    }
}
