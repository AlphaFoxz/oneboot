package com.github.alphafoxz.oneboot.core.domain;

import org.springframework.http.HttpStatus;

public class DomainAuthException extends DomainException {
    public DomainAuthException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
