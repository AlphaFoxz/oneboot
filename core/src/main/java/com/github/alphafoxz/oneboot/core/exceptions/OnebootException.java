package com.github.alphafoxz.oneboot.core.exceptions;

import com.github.alphafoxz.oneboot.core.toolkit.coding.MapUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@Getter
@Setter
public abstract class OnebootException extends RuntimeException implements RestfulApiException {
    private ResponseEntity<?> responseEntity;

    public OnebootException(String msg, HttpStatusCode httpStatus) {
        super(msg);
        Map<String, Object> body = MapUtil.newHashMap(4);
        body.put("message", msg);
        body.put("code", httpStatus.value());
        this.responseEntity = new ResponseEntity<>(body, httpStatus);
    }

    public OnebootException(String msg, HttpStatusCode httpStatus, Throwable throwable) {
        super(msg, throwable);
        if (msg != null && throwable != null) {
            msg += throwable.getMessage();
        }
        Map<String, Object> body = MapUtil.newHashMap(4);
        body.put("message", msg);
        body.put("code", httpStatus.value());
        this.responseEntity = new ResponseEntity<>(body, httpStatus);
    }
}
