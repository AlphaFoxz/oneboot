package com.github.alphafoxz.oneboot.domain.app.standard;

import com.github.alphafoxz.oneboot.domain.app.aggregate.ParkingAgg;
import org.springframework.stereotype.Component;

@Component
public interface ParkingRepository {
    public ParkingAgg findByIdOrError(Plate plate);

    public void save(ParkingAgg parkingAgg);
}
