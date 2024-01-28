package com.github.alphafoxz.oneboot.core.exceptions;

import org.springframework.http.HttpStatusCode;

/**
 * 代码生成异常
 */
public class OnebootGenException extends OnebootException {
    public OnebootGenException(String msg, HttpStatusCode httpStatus) {
        super(msg, httpStatus);
    }

    public OnebootGenException(String msg, HttpStatusCode httpStatus, Throwable throwable) {
        super(msg, httpStatus, throwable);
    }
}
