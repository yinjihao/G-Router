package com.example.mysterious.router.options;

import android.support.annotation.NonNull;

//处理Uri的base类
public class RootUriHandler {

    public void startUri(@NonNull UriRequest request) {
        //TODO：需要对Request进行判空
        handle(request);
    }

    //处理request
    private void handle(UriRequest request) {

    }
}
