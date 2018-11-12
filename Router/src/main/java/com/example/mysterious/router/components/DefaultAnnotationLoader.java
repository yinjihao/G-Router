package com.example.mysterious.router.components;


import com.example.mysterious.router.Router;
import com.example.mysterious.router.base.UriHandler;

import java.util.List;

/**
 * 使用ServiceLoader加载注解配置
 *
 */
public class DefaultAnnotationLoader implements AnnotationLoader {

    public static final AnnotationLoader INSTANCE = new DefaultAnnotationLoader();

    @Override
    public <T extends UriHandler> void load(T handler,
                                            Class<? extends AnnotationInit<T>> initClass) {
        List<? extends AnnotationInit<T>> services = Router.getAllServices(initClass);
        for (AnnotationInit<T> service : services) {
            service.init(handler);
        }
    }
}
