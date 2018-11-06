package com.example.mysterious.opensourcedemo;

import android.app.Application;

import com.sankuai.waimai.router.Router;
import com.sankuai.waimai.router.common.DefaultRootUriHandler;

public class TestApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化
        DefaultRootUriHandler defaultRootUriHandler = new DefaultRootUriHandler(this);

        Router.init(defaultRootUriHandler);
        //
    }
}
