package com.github.alphafoxz.oneboot.core.domain;

public interface DomainEventPublisher {
    public void publishEvent(DomainEvent event);
}
