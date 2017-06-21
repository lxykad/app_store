package com.lxy.shop.di.module;

import com.lxy.shop.data.api.ApiService;
import com.lxy.shop.ui.login.contract.LoginContract;
import com.lxy.shop.ui.login.model.LoginModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lxy on 2017/6/20.
 */

@Module
public class LoginModule {

    public LoginContract.LoginView mView;

    public LoginModule(LoginContract.LoginView mView) {
        this.mView = mView;
    }

    @Provides
    public LoginContract.LoginView provideView(){
        return mView;
    }

    @Provides
    public LoginModel provideModel(ApiService apiService){

        return new LoginModel(apiService);
    }
}
