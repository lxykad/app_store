package com.lxy.shop.di.module;

import com.lxy.shop.data.api.ApiService;
import com.lxy.shop.ui.ranking.RankingModel;
import com.lxy.shop.ui.ranking.contract.RankingContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lxy on 2017/6/16.
 */

@Module
public class AppInfoMoudle {

    public RankingContract.AppinfoView mView;

    public AppInfoMoudle(RankingContract.AppinfoView view){
        mView = view;
    }

    @Provides
    public RankingContract.AppinfoView provideView(){
        return mView;
    }

    @Provides
    public RankingModel provideMoudel(ApiService apiService){

        return new RankingModel(apiService);
    }

}
