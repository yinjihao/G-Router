package com.example.mysterious.router.options;

import android.support.annotation.NonNull;

/**
 * @author yinjihao
 * 拦截URI跳转并做处理，支持异步操作
 */
public interface UriInterceptor {

    void intercept(@NonNull UriRequest request, @NonNull UriCallback callback);
}
