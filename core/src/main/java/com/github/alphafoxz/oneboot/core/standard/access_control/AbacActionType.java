package com.github.alphafoxz.oneboot.core.standard.access_control;

/**
 * 访问控制操作类型
 */
public interface AbacActionType {
    /**
     * 创建
     */
    public static final String CREATE = "C";
    /**
     * 读取
     */
    public static final String READ = "R";
    /**
     * 更新
     */
    public static final String UPDATE = "U";
    /**
     * 逻辑删除
     */
    public static final String LOGIC_DELETE = "LD";
    /**
     * 删除
     */
    public static final String DELETE = "D";

    /**
     * 是否为只读性的操作
     */
    public static boolean isReadonlyAction(String action) {
        return READ.equals(action);
    }

    /**
     * 是否为更新性质的操作
     */
    public static boolean isUpdatableAction(String action) {
        return CREATE.equals(action) || UPDATE.equals(action) || LOGIC_DELETE.equals(action) || DELETE.equals(action);
    }
}
