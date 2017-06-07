package com.lxy.shop.ui.recommend;

import com.lxy.shop.common.rx.BaseBean;
import com.lxy.shop.common.rx.PageBean;
import com.lxy.shop.data.api.ApiService;

import io.reactivex.Observable;


/**
 * Created by lxy on 2017/5/11.
 */

public class RecommendModel{

    private ApiService mApiService;


    public RecommendModel(ApiService mApiService) {
        this.mApiService = mApiService;
    }

    public Observable<BaseBean<PageBean<AppBean>>> getApps(){

       return mApiService.getRecommendApps("{'page':0}");
    }

}
