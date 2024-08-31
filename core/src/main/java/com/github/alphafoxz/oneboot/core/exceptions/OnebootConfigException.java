package com.github.alphafoxz.oneboot.core.exceptions;

import org.springframework.http.HttpStatusCode;

/**
 * 项目配置异常
 */
public class OnebootConfigException extends OnebootException {
    public OnebootConfigException(String msg, HttpStatusCode httpStatus) {
        super(msg, httpStatus);
    }

    public OnebootConfigException(String msg, HttpStatusCode httpStatus, Throwable throwable) {
        super(msg, httpStatus, throwable);
    }
}
