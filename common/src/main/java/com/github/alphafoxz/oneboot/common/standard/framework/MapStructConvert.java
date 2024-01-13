package com.github.alphafoxz.oneboot.common.standard.framework;

public interface MapStructConvert {
    /**
     * DateTime转Long表达式
     */
    public static final String CREATE_TIME = "java(source.createTime().now().toInstant().toEpochMilli())";
    public static final String UPDATE_TIME = "java(source.updateTime().now().toInstant().toEpochMilli())";
}
