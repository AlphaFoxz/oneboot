package com.github.alphafoxz.oneboot.domain.app.aggregate;

import com.github.alphafoxz.oneboot.domain.app.command.CheckInCommand;
import com.github.alphafoxz.oneboot.domain.app.command.CheckOutCommand;
import com.github.alphafoxz.oneboot.domain.app.command.NotifyPayCommand;
import com.github.alphafoxz.oneboot.domain.app.standard.DomainEventQueue;

import java.time.LocalDateTime;

public interface ParkingAgg {
    public Boolean handle(DomainEventQueue eventQueue, CheckInCommand command);

    public Boolean handle(DomainEventQueue eventQueue, CheckOutCommand command);

    public void handle(DomainEventQueue eventQueue, NotifyPayCommand command);

    public Integer calculateFeeNow(LocalDateTime now);

    public Boolean inPark();
}
