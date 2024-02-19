package com.github.alphafoxz.oneboot.preset_sys.gen.restl.dtos._compile_only;

/**
 * Abac动态授权信息
 */
public interface PsysAbacDynamicAuthDtoFields {
    /**
     * 主键
     */
    public static final String ID = "id";

    /**
     * 授权类型
     */
    public static final String AUTHORIZATION_TYPE = "authorizationType";

    /**
     * 授权主体属性集
     */
    public static final String SUBJECT_ATTR_SET = "subjectAttrSet";

    /**
     * 授权过期时间 yyyy-MM-dd HH:mm:ss
     */
    public static final String TIMEOUT_UNTIL = "timeoutUntil";

    /**
     * 资源Id
     */
    public static final String RESOURCE_ID = "resourceId";

    /**
     * 资源所有者主体Id
     */
    public static final String OWNER_SUBJECT_ID = "ownerSubjectId";

    /**
     * 授权目标主体Id
     */
    public static final String TARGET_SUBJECT_ID = "targetSubjectId";

}