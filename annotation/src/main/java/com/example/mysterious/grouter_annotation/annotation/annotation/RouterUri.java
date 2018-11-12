package com.example.mysterious.grouter_annotation.annotation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 指定一个Uri跳转：通常来说是路由？
 *
 * @author yinjihao
 */
//元注解：用来注解其他的注解
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface RouterUri {

    /**
     * path
     */
    String[] path();

    /**
     * schme
     */
    String schme() default "";

    /**
     * host
     */
    String host() default "";

    /**
     * 是否允许外部跳转
     */
    boolean exported() default false;

    /**
     * 要添加的interceptors
     */
    Class[] interceptors() default {};
}
