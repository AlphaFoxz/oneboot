package com.github.alphafoxz.oneboot.core.annotations.access_control;

import com.github.alphafoxz.oneboot.core.standard.access_control.AbacActionType;
import com.github.alphafoxz.oneboot.core.standard.access_control.AbacPolicy;
import org.jooq.impl.TableImpl;

import java.lang.annotation.*;

/**
 * 具有访问控制功能的方法（只推荐注解在CrudService中）
 *
 * @implNote XXX 待实现-更好的易用性：当操作类型为读时，支持一个自定义lambda表达式，可以根据实际业务查出0-n个基本字段，其余字段都用空填充。
 * 通常对应这样的需求“权限不足的用户只能看列表、不能看详情”
 * 如此，可以实现queryPage方法查分页数据可以正常访问，而queryOne详情依旧返回403错误
 * @see AbacResourceBizId 配合AbacResourceBizId注解使用
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessControl {
    /**
     * 资源对应的表（jooq表）
     */
    Class<? extends TableImpl<?>> tableClass();

    /**
     * 操作类型
     *
     * @see AbacActionType
     */
    String action();

    /**
     * 应用的策略，访问控制会依次执行这些策略，推荐把又简单的、又能够过滤掉大部分请求的策略排在数组前面
     */
    Class<? extends AbacPolicy>[] policies();
}
