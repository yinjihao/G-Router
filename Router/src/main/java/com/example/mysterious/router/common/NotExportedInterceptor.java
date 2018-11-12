package com.example.mysterious.router.common;

import android.support.annotation.NonNull;

import com.example.mysterious.router.base.UriCallback;
import com.example.mysterious.router.base.UriInterceptor;
import com.example.mysterious.router.base.UriRequest;
import com.example.mysterious.router.base.UriResult;
import com.example.mysterious.router.components.UriSourceTools;


/**
 * 节点的exported为false，不允许来自外部的跳转，拦截并返回 {@link UriResult#CODE_FORBIDDEN}

 */

public class NotExportedInterceptor implements UriInterceptor {

    public static final NotExportedInterceptor INSTANCE = new NotExportedInterceptor();

    private NotExportedInterceptor() {
    }

    @Override
    public void intercept(@NonNull UriRequest request, @NonNull UriCallback callback) {
        if (UriSourceTools.shouldHandle(request, false)) {
            callback.onNext();
        } else {
            callback.onComplete(UriResult.CODE_FORBIDDEN);
        }
    }
}
