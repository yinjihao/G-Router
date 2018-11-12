package com.example.mysterious.router.base;

import android.support.annotation.NonNull;

import com.example.mysterious.router.util.PriorityList;

import java.util.Iterator;
import java.util.List;

/**
 * @author yinjihao
 * 支持添加多个字 UriHandler ，按先后顺序依次异步执行 
 */
public class ChainedHandler extends UriHandler {

    //存储Handler的集合，按照优先级执行
    private final PriorityList<UriHandler> mHandlers = new PriorityList<>();

    //添加一个handler,按照优先级大小执行
    public ChainedHandler addChildHandler(@NonNull UriHandler handler, int priority) {
        mHandlers.addItem(handler, priority);
        return this;
    }

    protected List<UriHandler> getHandlers() {
        return mHandlers;
    }


    //是否处理Uri,通过判断Handler的集合的长度
    @Override
    protected boolean shouldHandle(@NonNull UriRequest request) {
        return !mHandlers.isEmpty();
    }

    //
    @Override
    protected void handleInternal(@NonNull UriRequest request, @NonNull UriCallback callback) {
        //按照优先级依次处理request
        next(mHandlers.iterator(), request, callback);
    }

    private void next(final Iterator<UriHandler> iterator, final UriRequest request, final UriCallback callback) {

        if (iterator.hasNext()) {
            final UriHandler next = iterator.next();
            next.handleInternal(request, new UriCallback() {
                @Override
                public void onNext() {
                    //依次执行完相当于
                    next(iterator, request, callback);
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
