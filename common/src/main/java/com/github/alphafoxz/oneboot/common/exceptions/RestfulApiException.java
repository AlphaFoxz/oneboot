package com.github.alphafoxz.oneboot.common.exceptions;

import org.springframework.http.ResponseEntity;

public interface RestfulApiException {
    ResponseEntity<?> getResponseEntity();

    void setResponseEntity(ResponseEntity<?> responseEntity);
}
