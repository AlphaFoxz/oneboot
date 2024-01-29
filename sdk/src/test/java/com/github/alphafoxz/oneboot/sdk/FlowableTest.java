package com.github.alphafoxz.oneboot.sdk;

import com.github.alphafoxz.oneboot.core.standard.starter.flowable.FlowableService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FlowableTest {
    @Resource
    private FlowableService flowableService;

    @Test
    public void test() {

    }
}
