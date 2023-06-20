package com.wetoband.template.annotation;


import com.wetoband.template.constant.LogOperateType;

import java.lang.annotation.*;

/**
 * 日志操作注解，配合切面使用，主要只有在spring环境中切面才能生效
 */
@Documented // 定义注解的保留策略
@Inherited // 说明子类可以继承父类中的该注解
@Retention(RetentionPolicy.RUNTIME) // 定义注解的保留策略
@Target(ElementType.METHOD) // 定义注解的作用目标
public @interface LogOperate {

    String value() default "";

    LogOperateType type() default LogOperateType.QUERY;

}