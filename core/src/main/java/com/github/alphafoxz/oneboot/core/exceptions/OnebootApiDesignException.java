package com.github.alphafoxz.oneboot.core.exceptions;

import org.springframework.http.HttpStatusCode;

/**
 * API设计异常
 */
public class OnebootApiDesignException extends OnebootException {
    public OnebootApiDesignException(String msg, HttpStatusCode httpStatus) {
        super(msg, httpStatus);
    }

    public OnebootApiDesignException(String msg, HttpStatusCode httpStatus, Throwable throwable) {
        super(msg, httpStatus, throwable);
    }
}
