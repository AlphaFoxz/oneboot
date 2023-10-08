package com.github.alphafoxz.oneboot.common.exceptions;

import org.springframework.http.HttpStatusCode;

/**
 * 代码生成异常
 */
public class OnebootGenCodeException extends OnebootException {
    public OnebootGenCodeException(String msg, HttpStatusCode httpStatus) {
        super(msg, httpStatus);
    }

    public OnebootGenCodeException(String msg, HttpStatusCode httpStatus, Throwable throwable) {
        super(msg, httpStatus, throwable);
    }
}
