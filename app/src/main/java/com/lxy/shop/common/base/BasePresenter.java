package com.lxy.shop.common.base;

/**
 * Created by lxy on 2017/5/11.
 */

public class BasePresenter<M,V extends BaseView> {

    protected M mModel;
    protected V mView;

    public BasePresenter(M mModel, V mView) {
        this.mModel = mModel;
        this.mView = mView;
    }
}
