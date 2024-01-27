package com.github.alphafoxz.oneboot.core.exceptions;

import org.springframework.http.ResponseEntity;

public interface RestfulApiException {
    ResponseEntity<?> getResponseEntity();

    void setResponseEntity(ResponseEntity<?> responseEntity);
}
