package com.github.alphafoxz.oneboot.domain.app.command;

import com.github.alphafoxz.oneboot.domain.app.standard.DomainEventQueue;
import com.github.alphafoxz.oneboot.domain.app.standard.ParkingRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class CheckInCommandHandler {
    @Resource
    private ParkingRepository parkingRepository;

    public Boolean handle(DomainEventQueue eventQueue, CheckInCommand command) {
        var parkingAgg = parkingRepository.findByIdOrError(command.getPlate());
        var result = parkingAgg.handle(eventQueue, command);
        parkingRepository.save(parkingAgg);
        return result;
    }
}
