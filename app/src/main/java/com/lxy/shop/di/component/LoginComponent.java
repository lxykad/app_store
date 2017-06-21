package com.lxy.shop.di.component;

import com.lxy.shop.di.module.AppModule;
import com.lxy.shop.di.module.LoginModule;
import com.lxy.shop.di.scope.ActivityScope;
import com.lxy.shop.ui.login.LoginActivity;

import dagger.Component;

/**
 * Created by lxy on 2017/6/20.
 */

@ActivityScope
@Component(modules = LoginModule.class,dependencies = AppComponent.class)
public interface LoginComponent {

    void inject(LoginActivity activity);
}
