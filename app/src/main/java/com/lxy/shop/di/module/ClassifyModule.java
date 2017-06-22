package com.lxy.shop.di.module;

import com.lxy.shop.data.api.ApiService;
import com.lxy.shop.ui.classify.constract.ClassifyConstract;
import com.lxy.shop.ui.classify.model.ClassifyModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lxy on 2017/6/22.
 */

@Module
public class ClassifyModule {

    public ClassifyConstract.ClassifyView mView;

    public ClassifyModule(ClassifyConstract.ClassifyView classifyView) {
        this.mView = classifyView;
    }

    @Provides
    public ClassifyConstract.ClassifyView providesView() {
        return mView;
    }

    @Provides
    public ClassifyModel providesModel(ApiService apiService) {

        return new ClassifyModel(apiService);
    }
}
