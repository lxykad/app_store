package com.lxy.shop.ui.login.contract;

import com.lxy.shop.common.base.BaseView;
import com.lxy.shop.ui.login.bean.LoginBean;

/**
 * Created by lxy on 2017/6/20.
 */

public interface LoginContract {

    interface LoginView extends BaseView{

        void loginSuccess(LoginBean bean);
    }
}
