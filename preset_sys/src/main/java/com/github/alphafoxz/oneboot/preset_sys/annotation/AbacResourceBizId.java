package com.github.alphafoxz.oneboot.preset_sys.annotation;

import cn.hutool.core.annotation.Alias;

import java.lang.annotation.*;

/**
 * 访问控制资源Id（只推荐注解在CrudService中）
 * 基本规则
 * 1、当指定的资源拒绝被操作时，返回403错误
 * 2、如果注解在参数上，表示对应的参数为受保护的资源、且拥有id字段（或者其本身就是Long类型的id）。
 * 3、方法执行前就会对被注解的入参进行验证，如果不通过则直接返回403
 * 4、如果注解在方法体上，表示返回值为受保护的资源、且拥有id字段（或者其本身就是Long类型的id）。
 * 5、TODO 当返回值需要被验证时应当保证开启事务，当返回值没有权限时应当实现事务的回滚
 *
 * @implNote 注意：不推荐在crudService中实现任何自定义方法，即使必须要写，也要保证入参和返回值是可通过id判断的资源。同时，任何时候都不应该写根据非主键实现的update或delete方法。
 * @implSpec XXX 待实现-更好的安全性，后续可根据此约定和其他特殊规则，针对重点表设计危险操作通知、SQL分析、数据恢复等功能
 * @see AccessControl 配合AccessControl注解使用
 */
@Documented
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AbacResourceBizId {
    /**
     * 主键名称（实际上约定必须为id，不要改）
     * 当你使用了这个属性，说明你的表设计没有遵循规范或者做了一个很差的接口设计
     */
    @Deprecated
    String value() default "id";

    /**
     * 主键名称（实际上约定必须为id，不要改）
     * 当你使用了这个属性，说明你的表设计没有遵循规范或者做了一个很差的接口设计
     */
    @Alias("id")
    String fieldName() default "id";

    /**
     * 被注解的资源是一个容器（支持Map/Set/List/Page等），但主要是防止Map类型产生歧义，通过遍历容器取出里面的id
     *
     * @implSpec 注意：除Page外，应始终保证容器的深度为1，不应该嵌套多层容器。嵌套多层容器应该始终放在crud以外的组件中，因为难于和数据库高效交互、高效缓存、同时降低了可维护性。如果你写出了这种方法，说明你做了个垃圾设计
     */
    boolean fromContainer() default false;
}
