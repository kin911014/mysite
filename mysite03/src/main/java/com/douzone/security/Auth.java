package com.douzone.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
//retention은 기간에 관한것
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
	String value() default "user";
	boolean test() default false;
}
