package com.lxy.shop.di.component;

import com.lxy.shop.di.module.ClassifyModule;
import com.lxy.shop.di.scope.ActivityScope;
import com.lxy.shop.ui.classify.ClassifyFragment;
import com.lxy.shop.ui.classify.model.ClassifyModel;

import dagger.Component;

/**
 * Created by lxy on 2017/6/22.
 */

@ActivityScope
@Component(modules = ClassifyModule.class, dependencies = AppComponent.class)
public interface ClassifyComponent {

    void inject(ClassifyFragment fragment);
}
