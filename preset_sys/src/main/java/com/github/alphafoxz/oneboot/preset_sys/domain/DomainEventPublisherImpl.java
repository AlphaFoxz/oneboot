package com.github.alphafoxz.oneboot.preset_sys.domain;

import com.github.alphafoxz.oneboot.core.domain.DomainEvent;
import com.github.alphafoxz.oneboot.core.domain.DomainEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DomainEventPublisherImpl implements DomainEventPublisher {
    private final ApplicationContext applicationContext;

    @Override
    public void publishEvent(DomainEvent event) {
        applicationContext.publishEvent(event);
    }
}
