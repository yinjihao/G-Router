package com.example.mysterious.router.components;


import com.example.mysterious.router.base.UriHandler;



public interface AnnotationInit<T extends UriHandler> {

    void init(T handler);
}
