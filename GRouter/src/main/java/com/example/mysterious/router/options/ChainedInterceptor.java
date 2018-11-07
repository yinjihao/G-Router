package com.example.mysterious.router.options;

import android.support.annotation.NonNull;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yinjihao
 * 支持添加多个子interceptor ,按先后顺序依次异步执行
 * 仿照okhttp的请求
 */
public class ChainedInterceptor implements UriInterceptor {

    private final List<UriInterceptor> mInterceptors = new LinkedList<>();


    @Override
    public void intercept(@NonNull UriRequest request, @NonNull UriCallback callback) {
        handle(mInterceptors.iterator(), request, callback);

    }

    //将每个拦截器假如到list集合中
    public void addInterceptor(@NonNull UriInterceptor interceptor) {
        if (interceptor != null) {
            mInterceptors.add(interceptor);
        }
    }

    //循环处理拦截器中的事件,将每个拦截器中的事件都处理好.
    private void handle(final Iterator<UriInterceptor> iterator, final UriRequest request, final UriCallback callback) {
        //如果list中有拦截器的话
        if (iterator.hasNext()) {
            //获取拦截器
            UriInterceptor interceptor = iterator.next();
            //处理其内部的逻辑
            interceptor.intercept(request, new UriCallback() {
                @Override
                public void onNext() {
                    handle(iterator, request, callback);
                }

                @Override
                public void onComplete(int resultCode) {
                    callback.onComplete(resultCode);
                }
            });
        } else {
            callback.onNext();
        }

    }


}
