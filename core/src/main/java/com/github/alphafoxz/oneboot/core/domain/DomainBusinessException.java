package com.github.alphafoxz.oneboot.core.domain;

import org.springframework.http.HttpStatus;

public class DomainBusinessException extends DomainException {
    public DomainBusinessException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
