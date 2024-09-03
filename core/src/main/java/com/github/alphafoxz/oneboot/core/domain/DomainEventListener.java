package com.github.alphafoxz.oneboot.core.domain;

public interface DomainEventListener<T extends DomainEvent> {
    public void onEvent(T event);
}
