package com.github.alphafoxz.oneboot.domain.app.aggregate;

import com.github.alphafoxz.oneboot.domain.app.command.CheckInCommand;
import com.github.alphafoxz.oneboot.domain.app.command.CheckOutCommand;
import com.github.alphafoxz.oneboot.domain.app.command.NotifyPayCommand;
import com.github.alphafoxz.oneboot.domain.app.event.*;
import com.github.alphafoxz.oneboot.domain.app.standard.DomainEventQueue;
import com.github.alphafoxz.oneboot.domain.app.standard.Plate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ParkingAggImpl implements ParkingAgg {
    private static final int FEE_PER_HOUR = 1;
    private Plate id;
    private LocalDateTime checkInTime;
    private LocalDateTime lastPayTime;
    private Integer totalPaid = 0;

    @Override
    public Boolean handle(DomainEventQueue eventQueue, CheckInCommand command) {
        if (inPark()) {
            eventQueue.enqueue(new CheckInFailedEvent(id, command.getCheckedInTime()));
            return false;
        }

        eventQueue.enqueue(new CheckedInEvent(id, command.getCheckedInTime()));
        this.checkInTime = command.getCheckedInTime();
        return true;
    }

    @Override
    public Boolean handle(DomainEventQueue eventQueue, CheckOutCommand command) {
        if (!inPark()) {
            eventQueue.enqueue(new CheckOutFailedEvent(id, command.getTime(), "车辆不在场"));
            return false;
        }
        if (calculateFeeNow(command.getTime()) > 0) {
            return false;
        }

        this.checkInTime = null;
        this.totalPaid = 0;
        this.lastPayTime = null;

        eventQueue.enqueue(new CheckedOutEvent(id, command.getTime()));
        return true;
    }

    @Override
    public void handle(DomainEventQueue eventQueue, NotifyPayCommand command) {
        if (!inPark()) {
            throw new RuntimeException("车辆不在场，不能付费");
        }

        lastPayTime = command.getPayTime();
        totalPaid += command.getAmount();

        eventQueue.enqueue(new PaidEvent(id, command.getAmount(), command.getPayTime()));
    }

    @Override
    public Integer calculateFeeNow(LocalDateTime now) {
        if (checkInTime == null) {
            throw new RuntimeException("车辆尚未入场");
        }
        var currentCheckInTime = checkInTime;
        if (lastPayTime == null) {
            return feeBetween(currentCheckInTime, now);
        }
        var lastPayTimeCurrent = lastPayTime;
        if (totalPaid < feeBetween(currentCheckInTime, lastPayTimeCurrent)) {
            return feeBetween(currentCheckInTime, now) - totalPaid;
        }
        if (lastPayTimeCurrent.plusMinutes(15).isAfter(now)) {
            return 0;
        }
        return feeBetween(currentCheckInTime, now) - totalPaid;
    }

    @Override
    public Boolean inPark() {
        return checkInTime != null;
    }

    private Integer feeBetween(LocalDateTime start, LocalDateTime end) {
        return hoursBetween(start, end) * FEE_PER_HOUR;
    }

    private Integer hoursBetween(LocalDateTime start, LocalDateTime end) {
        var minutes = Duration.between(start, end).toMinutes();
        var hours = minutes / 60;
        return Long.valueOf(hours * 60 == minutes ? hours : hours + 1).intValue();
    }
}
