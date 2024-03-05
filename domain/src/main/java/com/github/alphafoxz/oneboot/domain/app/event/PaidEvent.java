package com.github.alphafoxz.oneboot.domain.app.event;

import com.github.alphafoxz.oneboot.domain.app.standard.Plate;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PaidEvent implements DomainEvent {
    private Plate plate;
    private Integer amount;
    private LocalDateTime payTime;
}
