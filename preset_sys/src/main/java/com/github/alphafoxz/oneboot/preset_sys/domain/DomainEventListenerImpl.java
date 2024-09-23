package com.github.alphafoxz.oneboot.preset_sys.domain;

import com.github.alphafoxz.oneboot.core.domain.DomainEvent;
import com.github.alphafoxz.oneboot.core.domain.DomainEventListener;
import org.springframework.stereotype.Component;

@Component
public class DomainEventListenerImpl implements DomainEventListener<DomainEvent> {
    @Override
    public void onEvent(DomainEvent event) {
        // TODO 持久化
    }
}
