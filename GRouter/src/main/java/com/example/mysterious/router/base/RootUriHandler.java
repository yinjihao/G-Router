package com.example.mysterious.router.base;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

//处理Uri的base类
public class RootUriHandler extends ChainedHandler {

    private onCompleteListener mGlobalOnCompleteListener;

    private final Context mContext;

    public RootUriHandler(Context context) {
        mContext = context.getApplicationContext();
    }

    //设置全局的回调
    public void setGlobaOnCompleteListener(onCompleteListener listener) {
        mGlobalOnCompleteListener = listener;
    }


    public void startUri(@NonNull UriRequest request) {
        //TODO：需要对Request进行判空
        if (request == null) {

            String msg = "UriRequest为空";
            //Debugger.fatal(msg);
            UriRequest req = new UriRequest(mContext, Uri.EMPTY).setErrorMessage(msg);
            onError(req, UriResult.CODE_BAD_REQUEST);

        } else if (request.getContext() == null) {

            String msg = "UriRequest.Context为空";
            // Debugger.fatal(msg);
            UriRequest req = new UriRequest(mContext, request.getUri(), request.getFields())
                    .setErrorMessage(msg);
            onError(req, UriResult.CODE_BAD_REQUEST);

        } else if (request.isUriEmpty()) {

            String msg = "跳转链接为空";
            // Debugger.e(msg);
            request.setErrorMessage(msg);
            onError(request, UriResult.CODE_BAD_REQUEST);

        } else {

            /*if (Debugger.isEnableLog()) {
                Debugger.i("");
                Debugger.i("---> receive request: %s", request.toFullString());
            }*/
            handleUri(request, new RootUriCallback(request));
        }
    }


    private void onError(UriRequest mRequest, int resultCode) {
        onCompleteListener listener = mGlobalOnCompleteListener;
        if (listener != null) {
            listener.onError(mRequest, resultCode);
        }
        onCompleteListener onCompleteListener = mRequest.getOnCompleteListener();
        if (onCompleteListener != null) {
            onCompleteListener.onError(mRequest, resultCode);
        }
    }

    private void onSuccess(UriRequest mRequest) {
        onCompleteListener globalListener = mGlobalOnCompleteListener;
        if (globalListener != null) {
            globalListener.onSuccess(mRequest);
        }
        final onCompleteListener listener = mRequest.getOnCompleteListener();
        if (listener != null) {
            listener.onSuccess(mRequest);
        }
    }


    @Override
    public RootUriHandler addChildHandler(@NonNull UriHandler handler, int priority) {
        return (RootUriHandler) super.addChildHandler(handler, priority);
    }

    protected class RootUriCallback implements UriCallback {

        private final UriRequest mRequest;

        public RootUriCallback(UriRequest request) {
            mRequest = request;
        }

        @Override
        public void onNext() {
            onComplete(UriResult.CODE_NOT_FOUND);
        }

        @Override
        public void onComplete(int resultCode) {
            switch (resultCode) {

                case UriResult.CODE_REDIRECT:
                    // 重定向，重新跳转
                    startUri(mRequest);
                    break;

                case UriResult.CODE_SUCCESS:
                    // 跳转成功
                    mRequest.putField(UriRequest.FIELD_RESULT_CODE, resultCode);
                    onSuccess(mRequest);
                    break;

                default:
                    // 跳转失败
                    mRequest.putField(UriRequest.FIELD_RESULT_CODE, resultCode);
                    onError(mRequest, resultCode);
                    break;
            }
        }
    }


}
