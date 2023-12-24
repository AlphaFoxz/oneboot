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

    /**
     * 基于前端不可信这个原则，对于有默认值的属性每次都判空会导致大量重复代码
     * 所以提供一些简单的方法
     * Tips: 对于不可口空的参数则应该自己根据实际情况返回http错误码
     */
    public static class U {
        public static int intVal(Integer input, int defaultValue) {
            return input == null ? defaultValue : input;
        }

        public static Double doubleVal(Double input, Double defaultValue) {
            return input == null ? defaultValue : input;
        }

        public static String strVal(String input, String defaultValue) {
            return input == null ? defaultValue : input;
        }

        public static boolean boolVal(Boolean input, boolean defaultValue) {
            return input == null ? defaultValue : input;
        }
    }
}
