package com.github.alphafoxz.oneboot.core.exceptions;

import org.springframework.http.HttpStatus;

public class OnebootNotFoundException extends OnebootException {
    public OnebootNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
