package com.github.alphafoxz.oneboot.domain.preset_sys;

import cn.hutool.extra.spring.SpringUtil;

public interface DomainEventPublisher {
    public static DomainEventPublisher getInstance() {
        return SpringUtil.getBean(DomainEventPublisher.class);
    }

    public void publishEvent(DomainEvent event);
}
