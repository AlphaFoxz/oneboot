package com.github.alphafoxz.oneboot.domain.app.standard;

import com.github.alphafoxz.oneboot.domain.app.event.DomainEvent;

import java.util.List;

public interface DomainEventQueue {
    public void enqueue(DomainEvent event);

    public List<DomainEvent> queue();
}
