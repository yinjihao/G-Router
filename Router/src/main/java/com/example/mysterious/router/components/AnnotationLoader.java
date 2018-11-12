package com.example.mysterious.router.components;


import com.example.mysterious.router.base.UriHandler;

/**
 * 用于加载注解配置
 *
 */
public interface AnnotationLoader {

    <T extends UriHandler> void load(T handler, Class<? extends AnnotationInit<T>> initClass);
}
