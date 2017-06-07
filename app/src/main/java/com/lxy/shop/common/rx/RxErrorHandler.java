package com.lxy.shop.common.rx;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.JsonParseException;
import com.lxy.shop.common.exception.ApiException;
import com.lxy.shop.common.exception.BaseException;
import com.lxy.shop.common.exception.ErrorMessageFactory;

import java.net.SocketException;
import java.net.SocketTimeoutException;

import retrofit2.HttpException;

/**
 * Created by lxy on 2017/6/7.
 */

public class RxErrorHandler {

    private Context mContext;

    public RxErrorHandler(Context context) {

        this.mContext = context;
    }

    public BaseException handleError(Throwable e) {

        BaseException exception = new BaseException();

        if (e instanceof ApiException) {

            exception.setCode(((ApiException) e).getCode());

        } else if (e instanceof JsonParseException) {

            exception.setCode(BaseException.JSON_ERROR);

        } else if (e instanceof HttpException) {

            exception.setCode(((HttpException) e).code());

        } else if (e instanceof SocketTimeoutException) {

            exception.setCode(BaseException.SOCKET_TIMEOUT_ERROR);

        } else if (e instanceof SocketException) {


        } else {

            exception.setCode(BaseException.UNKNOWN_ERROR);

        }

        exception.setDisplayMessage(ErrorMessageFactory.create(exception.getCode()));

        return exception;
    }

    public void showErrorMessage(BaseException e) {

        Toast.makeText(mContext, e.getDisplayMessage(), Toast.LENGTH_LONG).show();

    }
}
