package com.annotation.demo3;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
// 类，接口或enum
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLString {
	int value() default 0;

	String name() default "";

	// 注解元素中引用别的注解，
	Constraints constraints() default @Constraints;
}