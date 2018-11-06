package com.example.mysterious.grouter_annotation.annotation;

/**
 * 声明一个Service,通过interface和key加载实现类。此注解可以用在任意静态类上
 *
 * @author yinjihao
 */
public @interface RouterService {

    /**
     * 实现的接口(或继承的父类)
     */
    Class[] interfaces();

    /**
     * 同一个接口的多个实现类之间，可以通过唯一的key
     */
    String[] key() default {};

    /**
     * 是否为单例.如果是单例，则使用ServiceLoader.getService 不会重复创建实例。
     */
    boolean singleton() default false;
}
