package com.github.alphafoxz.oneboot.domain.preset_sys;

import org.springframework.http.HttpStatusCode;

public class DomainVoException extends DomainException {
    public DomainVoException(String msg, HttpStatusCode httpStatus) {
        super(msg, httpStatus);
    }
}
