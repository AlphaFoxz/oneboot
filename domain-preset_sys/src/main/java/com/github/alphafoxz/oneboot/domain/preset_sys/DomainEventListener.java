package com.github.alphafoxz.oneboot.domain.preset_sys;

public interface DomainEventListener<T extends DomainEvent> {
    public void onEvent(T event);
}
