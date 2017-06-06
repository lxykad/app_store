package com.lxy.shop.common.rx;

import java.io.Serializable;

/**
 * Created by lxy on 2017/5/19.
 */

public class BaseBean<T> implements Serializable {

    public static final int SUCCESS = 1;

    public boolean error;
    public T data;
    public int status;

    public boolean isStatusOk() {
        return status == SUCCESS;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "error=" + error +
                ", data=" + data +
                ", status=" + status +
                '}';
    }
}
