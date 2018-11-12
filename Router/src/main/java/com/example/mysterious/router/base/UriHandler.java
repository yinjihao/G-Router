package com.example.mysterious.router.base;

import android.support.annotation.NonNull;

/**
 * @author yinjihao
 * 处理某一类或某个URI,支持添加若干个interceptor
 */
public abstract class UriHandler {
    //链式的拦截器
    protected ChainedInterceptor mInterceptor;

    //将拦截器添加到
    public UriHandler addInterceptor(@NonNull UriInterceptor interceptor) {
        if (interceptor != null) {
            if (mInterceptor == null) {
                mInterceptor = new ChainedInterceptor();
            }
            mInterceptor.addInterceptor(interceptor);
        }
        return this;
    }

    public UriHandler addInterceptors(UriInterceptor... interceptors) {
        if (interceptors != null && interceptors.length > 0) {
            if (mInterceptor == null) {
                mInterceptor = new ChainedInterceptor();
            }
            for (UriInterceptor interceptor : interceptors) {
                mInterceptor.addInterceptor(interceptor);
            }
        }
        return this;
    }

    /**
     * 处理URI，通常不需要覆盖本
     */
    public void handleUri(@NonNull final UriRequest request, @NonNull final UriCallback callback) {
        if (shouldHandle(request)) {
            if (mInterceptor != null && request.isSkipInterceptors()) {
                mInterceptor.intercept(request, new UriCallback() {
                    @Override
                    public void onNext() {
                        handleInternal(request, callback);
                    }

                    @Override
                    public void onComplete(int resultCode) {
                        callback.onComplete(resultCode);
                    }
                });
            }
        }
    }


    /**
     * 是否要处理给定的URI，在 UriInterceptor之前调用
     */
    protected abstract boolean shouldHandle(@NonNull UriRequest request);

    /**
     * 处理URI.在 UriInterceptor之后调用
     **/
    protected abstract void handleInternal(@NonNull UriRequest request, @NonNull UriCallback callback);


}
