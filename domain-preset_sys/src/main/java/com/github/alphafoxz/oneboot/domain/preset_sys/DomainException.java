package com.github.alphafoxz.oneboot.domain.preset_sys;

import com.github.alphafoxz.oneboot.core.exceptions.OnebootException;
import org.springframework.http.HttpStatusCode;

public class DomainException extends OnebootException {
    public DomainException(String msg, HttpStatusCode httpStatus) {
        super(msg, httpStatus);
    }
}
