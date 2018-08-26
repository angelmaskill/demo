package com.annotation.demo1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Package declaration
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.PACKAGE)
public @interface MyPackageAnnoation {

}