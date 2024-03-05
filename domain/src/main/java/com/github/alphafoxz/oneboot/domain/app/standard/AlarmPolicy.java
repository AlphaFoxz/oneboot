package com.github.alphafoxz.oneboot.domain.app.standard;

import com.github.alphafoxz.oneboot.domain.app.event.CheckInFailedEvent;
import com.github.alphafoxz.oneboot.domain.app.event.CheckOutFailedEvent;
import com.github.alphafoxz.oneboot.domain.app.event.DomainEvent;
import com.github.alphafoxz.oneboot.domain.app.event.DomainEventListener;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class AlarmPolicy implements DomainEventListener {
    @Resource
    private AlarmService alarmService;

    @Override
    public void onEvent(DomainEvent event) {
        if (event instanceof CheckInFailedEvent checkInFailedEvent) {
            alarmService.alarm(checkInFailedEvent.getPlate(), "入场失败");
            return;
        }
        if (event instanceof CheckOutFailedEvent checkOutFailedEvent) {
            alarmService.alarm(checkOutFailedEvent.getPlate(), checkOutFailedEvent.getMessage());
        }
    }
}
