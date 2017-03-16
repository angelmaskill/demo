package com.annotation.demo3;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
// 类，接口或enum
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLInteger {
	String name() default "";

	// 嵌套注解的功能,将column类型的数据库约束信息嵌入其中
	Constraints constraints() default @Constraints;
}