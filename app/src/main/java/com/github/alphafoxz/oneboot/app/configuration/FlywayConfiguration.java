package com.github.alphafoxz.oneboot.app.configuration;

import lombok.SneakyThrows;
import org.flywaydb.core.api.callback.Callback;
import org.flywaydb.core.api.callback.Context;
import org.flywaydb.core.api.callback.Event;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfiguration implements Callback {
    @Override
    @SneakyThrows
    public boolean supports(Event event, Context context) {
        return event != Event.BEFORE_CLEAN;
    }

    @Override
    public boolean canHandleInTransaction(Event event, Context context) {
        return true;
    }

    @Override
    public void handle(Event event, Context context) {

    }

    @Override
    public String getCallbackName() {
        return "";
    }


}
