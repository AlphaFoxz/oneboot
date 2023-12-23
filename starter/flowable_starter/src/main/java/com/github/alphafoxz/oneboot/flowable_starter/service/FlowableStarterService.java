package com.github.alphafoxz.oneboot.flowable_starter.service;

import com.github.alphafoxz.oneboot.common.standard.starter.flowable.FlowableService;
import jakarta.annotation.Resource;
import org.flowable.engine.ProcessEngine;
import org.springframework.stereotype.Service;

@Service
public class FlowableStarterService implements FlowableService {
    @Resource
    private ProcessEngine processEngine;
}
