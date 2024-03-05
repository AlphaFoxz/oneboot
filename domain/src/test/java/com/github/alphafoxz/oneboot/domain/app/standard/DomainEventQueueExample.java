package com.github.alphafoxz.oneboot.domain.app.standard;

import com.github.alphafoxz.oneboot.domain.app.event.DomainEvent;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
public class DomainEventQueueExample implements DomainEventQueue {
    private final LinkedList<DomainEvent> list = new LinkedList<>();

    @Override
    public void enqueue(DomainEvent event) {
        list.add(event);
    }

    @Override
    public List<DomainEvent> queue() {
        return list;
    }
}