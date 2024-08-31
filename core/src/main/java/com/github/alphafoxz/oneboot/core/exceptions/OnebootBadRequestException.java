package com.github.alphafoxz.oneboot.core.exceptions;

import org.springframework.http.HttpStatusCode;

public class OnebootBadRequestException extends OnebootException {
    public OnebootBadRequestException(String msg, HttpStatusCode httpStatus) {
        super(msg, httpStatus);
    }

    public OnebootBadRequestException(String msg, HttpStatusCode httpStatus, Throwable throwable) {
        super(msg, httpStatus, throwable);
    }
}
