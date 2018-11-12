package com.example.mysterious.router.base;

import android.support.annotation.NonNull;

/**
 * @author yinjihao
 */
public interface onCompleteListener extends UriResult {

    //分发成功
    void onSuccess(@NonNull UriRequest request);

    //分发失败
    void onError(@NonNull UriRequest request, int resultCode);
}
