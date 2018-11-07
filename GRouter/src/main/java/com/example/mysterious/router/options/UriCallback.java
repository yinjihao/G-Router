package com.example.mysterious.router.options;

/**
 * @author yinjihao
 * 异步事件处理完成的回调
 */
public interface UriCallback {
    /**
     * 处理完成，继续后续流程
     */
    void onNext();

    /**
     * 处理完成，终止分发流程
     */
    void onComplete(int resultCode);
}
