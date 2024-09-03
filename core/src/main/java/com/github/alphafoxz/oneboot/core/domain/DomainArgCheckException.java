package com.github.alphafoxz.oneboot.core.domain;

import org.springframework.http.HttpStatus;

public class DomainArgCheckException extends DomainException {
    public DomainArgCheckException(String msg) {
        super(msg, HttpStatus.BAD_REQUEST);
    }
}
