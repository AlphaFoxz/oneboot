package com.github.alphafoxz.oneboot.core.domain;

import cn.hutool.extra.spring.SpringUtil;

public interface DomainEventPublisher {
    class Instance {
        private static DomainEventPublisher VALUE = null;
    }

    public static DomainEventPublisher getInstance() {
        if (Instance.VALUE == null) {
            Instance.VALUE = SpringUtil.getBean(DomainEventPublisher.class);
        }
        return Instance.VALUE;
    }

    public static void setInstance(DomainEventPublisher instance) {
        if (Instance.VALUE != null) {
            throw new RuntimeException("Instance already exists");
        }
        Instance.VALUE = instance;
    }

    public void publishEvent(DomainEvent event);
}
