package com.github.alphafoxz.oneboot.domain.app.command;

import com.github.alphafoxz.oneboot.domain.app.standard.Plate;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CheckOutCommand {
    private Plate plate;
    private LocalDateTime time;
}
