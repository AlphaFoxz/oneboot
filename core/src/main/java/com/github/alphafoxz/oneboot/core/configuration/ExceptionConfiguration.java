package com.github.alphafoxz.oneboot.core.configuration;

import com.github.alphafoxz.oneboot.core.exceptions.OnebootException;
import com.github.alphafoxz.oneboot.core.toolkit.coding.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionConfiguration {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception e) {
        log.error(ExceptionUtil.stacktraceToString(e));
        if (e instanceof OnebootException onebootException) {
            return onebootException.getResponseEntity();
        }
        return ResponseEntity.internalServerError().body(e.getMessage());
    }
}
