package com.github.alphafoxz.oneboot.core.exceptions;

import org.springframework.http.HttpStatus;

/**
 * 脏数据异常
 */
public class OnebootDirtyDataException extends OnebootException {
    public OnebootDirtyDataException(String msg) {
        super(msg, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
