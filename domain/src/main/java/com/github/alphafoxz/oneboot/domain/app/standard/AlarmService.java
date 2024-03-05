package com.github.alphafoxz.oneboot.domain.app.standard;

import org.springframework.stereotype.Component;

@Component
public interface AlarmService {
    public void alarm(Plate plate, String message);
}
