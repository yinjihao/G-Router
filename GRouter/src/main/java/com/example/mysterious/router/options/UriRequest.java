package com.example.mysterious.router.options;


import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.HashMap;

/**
 * @author yinjihao
 * 请求的统一封装类
 */
public class UriRequest {


    @NonNull
    private final Context mContext;

    @NonNull
    private Uri mUri;

    @NonNull
    private final HashMap<String, Object> mFields;

    /**
     * 是否跳过拦截器
     */
    private boolean isSkipInterceptors = false;


    public boolean isSkipInterceptors() {
        return isSkipInterceptors;
    }

    //构造器
    public UriRequest(Context context, Uri uri, HashMap<String, Object> fields) {
        mContext = context;
        mUri = uri == null ? Uri.EMPTY : uri;
        mFields = fields == null ? new HashMap<String, Object>() : fields;
    }




}
