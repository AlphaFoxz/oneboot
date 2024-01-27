package com.github.alphafoxz.oneboot.core.exceptions;

import org.springframework.http.HttpStatusCode;

/**
 * 脏数据异常
 */
public class OnebootDirtyDataException extends OnebootException {
    public OnebootDirtyDataException(String msg, HttpStatusCode httpStatus) {
        super(msg, httpStatus);
    }

    public OnebootDirtyDataException(String msg, HttpStatusCode httpStatus, Throwable throwable) {
        super(msg, httpStatus, throwable);
    }
}
