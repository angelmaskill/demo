package com.annotation.demo3;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
// 类，接口或enum
@Retention(RetentionPolicy.RUNTIME)
//定义表名的注解
public @interface DBTable {
    public String name() default "";
}