package com.github.alphafoxz.oneboot.common.standard.framework;

import org.springframework.http.HttpStatus;

public interface HttpController {
    public static HttpStatus CODE_200 = HttpStatus.OK;
    public static HttpStatus CODE_201 = HttpStatus.CREATED;
    public static HttpStatus CODE_204 = HttpStatus.NO_CONTENT;
    public static HttpStatus CODE_301 = HttpStatus.MOVED_PERMANENTLY;
    public static HttpStatus CODE_302 = HttpStatus.FOUND;
    public static HttpStatus CODE_400 = HttpStatus.BAD_REQUEST;
    public static HttpStatus CODE_401 = HttpStatus.UNAUTHORIZED;
    public static HttpStatus CODE_403 = HttpStatus.FORBIDDEN;
    public static HttpStatus CODE_404 = HttpStatus.NOT_FOUND;
    public static HttpStatus CODE_500 = HttpStatus.INTERNAL_SERVER_ERROR;
    public static HttpStatus CODE_501 = HttpStatus.NOT_IMPLEMENTED;
}
