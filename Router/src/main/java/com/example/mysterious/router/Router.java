package com.example.mysterious.router;


import android.content.Context;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.example.mysterious.router.base.RootUriHandler;
import com.example.mysterious.router.base.UriRequest;

//路由的核心类
//
public class Router {

    private static RootUriHandler ROOT_HANDLER;

    public static void init(@NonNull RootUriHandler rootUriHandler) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            //需要在主线程调用
        }
        if (ROOT_HANDLER == null) {
            ROOT_HANDLER = rootUriHandler;
        } else {
            //不要重复创建
        }
    }

    //基本的调用方式
    public static void startUri(UriRequest request) {
        getRootHandler().startUri(request);
    }

    public static void startUri(Context context, String uri) {
        getRootHandler().startUri(new UriRequest(context, uri));
    }

    public static RootUriHandler getRootHandler() {
        if (ROOT_HANDLER == null) {
            throw new RuntimeException("需要初始化Router");
        }
        return ROOT_HANDLER;
    }
}
