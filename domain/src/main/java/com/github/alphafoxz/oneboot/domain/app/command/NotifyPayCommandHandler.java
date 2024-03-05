package com.github.alphafoxz.oneboot.domain.app.command;

import com.github.alphafoxz.oneboot.domain.app.standard.DomainEventQueue;
import com.github.alphafoxz.oneboot.domain.app.standard.ParkingRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class NotifyPayCommandHandler {
    @Resource
    private ParkingRepository parkingRepository;

    public void handle(DomainEventQueue eventQueue, NotifyPayCommand command) {
        var parkingAgg = parkingRepository.findByIdOrError(command.getPlate());
        parkingAgg.handle(eventQueue, command);
        parkingRepository.save(parkingAgg);
    }
}
