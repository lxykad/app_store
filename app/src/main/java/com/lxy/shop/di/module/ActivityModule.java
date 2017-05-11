package com.lxy.shop.di.module;

import com.lxy.shop.data.api.ApiService;
import com.lxy.shop.ui.recommend.RecommendModel;
import com.lxy.shop.ui.recommend.contract.RecommendContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lxy on 2017/5/11.
 */

@Module
public class ActivityModule {

    private RecommendContract.View mView;

    public ActivityModule(RecommendContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public RecommendContract.View provideView(){

        return mView;
    }

    @Provides
    public RecommendModel provideModel(ApiService apiService){

        return new RecommendModel(apiService);
    }

}
