package com.github.alphafoxz.oneboot.core.exceptions;

import org.springframework.http.HttpStatusCode;

/**
 * 权限异常
 */
public class OnebootAuthException extends OnebootException {
    public OnebootAuthException(String msg, HttpStatusCode httpStatus) {
        super(msg, httpStatus);
    }

    public OnebootAuthException(String msg, HttpStatusCode httpStatus, Throwable throwable) {
        super(msg, httpStatus, throwable);
    }
}
