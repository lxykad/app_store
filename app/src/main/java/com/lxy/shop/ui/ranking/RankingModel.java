package com.lxy.shop.ui.ranking;

import com.lxy.shop.common.rx.BaseBean;
import com.lxy.shop.common.rx.PageBean;
import com.lxy.shop.data.api.ApiService;
import com.lxy.shop.ui.recommend.AppBean;

import io.reactivex.Observable;

/**
 * Created by lxy on 2017/6/16.
 */

public class RankingModel {

    private ApiService mApiService;


    public RankingModel(ApiService mApiService) {
        this.mApiService = mApiService;
    }

    public Observable<BaseBean<PageBean<AppBean>>> getData(int page) {

        return mApiService.getRankingList(page);
    }

}
