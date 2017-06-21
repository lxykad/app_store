package com.lxy.shop.ui.login.model;

import com.lxy.shop.common.rx.BaseBean;
import com.lxy.shop.data.api.ApiService;
import com.lxy.shop.ui.login.bean.LoginBean;
import com.lxy.shop.ui.login.bean.LoginRequestBean;

import io.reactivex.Observable;

/**
 * Created by lxy on 2017/6/20.
 */

public class LoginModel {

    private ApiService mApiservice;

    public LoginModel(ApiService mApiservice) {
        this.mApiservice = mApiservice;
    }

    public Observable<BaseBean<LoginBean>> login(String user_name, String pwd){

        LoginRequestBean bean = new LoginRequestBean();

        bean.setEmail(user_name);
        bean.setPassword(pwd);

        return mApiservice.login(bean);

    }
}
