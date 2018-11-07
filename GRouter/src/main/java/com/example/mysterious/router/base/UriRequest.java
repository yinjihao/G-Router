package com.example.mysterious.router.base;


import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.HashMap;

/**
 * @author yinjihao
 * 请求的统一封装类
 */
public class UriRequest {


    //跳转请求完成的回调
    public static final String FIELD_COMPLETE_LISTENER = "UriRequest_SUCCESS";

    //跳转请求的结果
    public static final String FIELD_RESULT_CODE = "UriRequest_RESULT";

    //跳转请求失败的信息
    public static final String FIELD_REEOR_MSG = "UriRequest_ERROR";


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


    public UriRequest(@NonNull Context context, Uri uri) {
        this(context, uri, new HashMap<String, Object>());
    }

    public UriRequest(@NonNull Context context, String uri) {
        this(context, parseUriSafely(uri), new HashMap<String, Object>());
    }

    //构造器
    public UriRequest(Context context, Uri uri, HashMap<String, Object> fields) {
        mContext = context;
        mUri = uri == null ? Uri.EMPTY : uri;
        mFields = fields == null ? new HashMap<String, Object>() : fields;
    }


    /**
     * 设置Extra参数
     */
    public <T> UriRequest putField(@NonNull String key, T val) {
        if (val != null) {
            mFields.put(key, val);
        }
        return this;
    }

    public <T> T getField(@NonNull Class<T> clazz, @NonNull String key, T defaultValue) {
        Object field = mFields.get(key);
        if (field != null) {
            try {
                return clazz.cast(field);
            } catch (ClassCastException e) {
                //  Debugger.fatal(e);
            }
        }
        return defaultValue;
    }

    @NonNull
    public HashMap<String, Object> getFields() {
        return mFields;
    }


    /**
     * 判断Uri是否为空。空的Uri会在RootHandler中被处理，其他Handler不需要关心。
     */
    public boolean isUriEmpty() {
        return Uri.EMPTY.equals(mUri);
    }

    public <T> T getField(@NonNull Class<T> clazz, @NonNull String key) {
        return getField(clazz, key, null);
    }

    public onCompleteListener getOnCompleteListener() {
        return getField(onCompleteListener.class, UriRequest.FIELD_COMPLETE_LISTENER);
    }

    public UriRequest setErrorMessage(String message) {
        putField(FIELD_REEOR_MSG, message);
        return this;
    }

    @NonNull
    public Context getContext() {
        return mContext;
    }

    @NonNull
    public Uri getUri() {
        return mUri;
    }

    private static Uri parseUriSafely(@Nullable String uri) {
        return TextUtils.isEmpty(uri) ? Uri.EMPTY : Uri.parse(uri);
    }
}
