package com.lxy.shop.ui.recommend;

import com.lxy.shop.data.api.ApiService;

import javax.inject.Inject;

import rx.Observable;


/**
 * Created by lxy on 2017/5/11.
 */

public class RecommendModel{

    private ApiService mApiService;


    public RecommendModel(ApiService mApiService) {
        this.mApiService = mApiService;
    }

    public Observable<AndroidBean> getApps(){

        //mApiService.getRecommendApps("{'page':0}");

       return mApiService.getAndroid("1");

    }

}
