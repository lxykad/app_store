package com.lxy.shop.common.rx.observer;

import android.util.Log;

import com.lxy.shop.common.exception.BaseException;
import com.lxy.shop.common.rx.RxErrorHandler;

/**
 * Created by lxy on 2017/6/7.
 */

public abstract class ErrorHandObserver<T> extends BaseObserver<T> {

    protected RxErrorHandler mErrorHandler = null;

    public ErrorHandObserver(RxErrorHandler handler) {
        mErrorHandler = handler;
    }

    @Override
    public void onError(Throwable e) {

        BaseException baseException = mErrorHandler.handleError(e);

        if (baseException == null) {

            e.printStackTrace();
            Log.d("ErrorHandObserver", e.getMessage());

        } else {
            mErrorHandler.showErrorMessage(baseException);
        }

    }
}
