package com.github.alphafoxz.oneboot.domain.app.event;

public interface DomainEventListener {
    public void onEvent(DomainEvent event);
}
