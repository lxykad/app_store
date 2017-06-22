package com.lxy.shop.ui.detail.presenter;

import com.lxy.shop.common.base.BasePresenter;
import com.lxy.shop.common.base.BaseView;

import javax.inject.Inject;

/**
 * Created by lxy on 2017/6/22.
 */

public class AppDetailPresenter extends BasePresenter {

    @Inject
    public AppDetailPresenter(Object mModel, BaseView mView) {
        super(mModel, mView);
    }
}
