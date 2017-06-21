package com.lxy.shop.common.rx.observer;

import android.content.Context;

import com.lxy.shop.common.exception.BaseException;
import com.lxy.shop.common.rx.ErrorHandler;

/**
 * Created by lxy on 2017/6/7.
 */

public abstract class ErrorHandObserver<T> extends BaseObserver<T> {

    protected ErrorHandler mErrorHandler = null;

    public ErrorHandObserver(Context context) {
       mErrorHandler = new ErrorHandler(context);
    }

    @Override
    public void onError(Throwable e) {

        BaseException baseException = mErrorHandler.handleError(e);

        if (baseException == null) {

            e.printStackTrace();

        } else {
            mErrorHandler.showErrorMessage(baseException);
        }

    }
}
