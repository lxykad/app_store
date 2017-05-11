package com.lxy.shop.di.component;

import com.lxy.shop.di.module.ActivityModule;
import com.lxy.shop.di.module.AppModule;
import com.lxy.shop.di.scope.ActivityScope;
import com.lxy.shop.ui.MainActivity;

import dagger.Component;

/**
 * Created by lxy on 2017/5/11.
 */
@ActivityScope
@Component(modules = ActivityModule.class,dependencies = AppComponent.class)
public interface ActivityComponent {

    void inject(MainActivity activity);
}
