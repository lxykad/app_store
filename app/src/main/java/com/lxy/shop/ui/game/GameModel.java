package com.lxy.shop.ui.game;

import com.lxy.shop.common.rx.BaseBean;
import com.lxy.shop.common.rx.PageBean;
import com.lxy.shop.data.api.ApiService;
import com.lxy.shop.ui.recommend.AppBean;

import io.reactivex.Observable;

/**
 * Created by lxy on 2017/6/16.
 */

public class GameModel {

    private ApiService mApiService;


    public GameModel(ApiService mApiService) {
        this.mApiService = mApiService;
    }

    public Observable<BaseBean<PageBean<AppBean>>> getData(int page) {

        return mApiService.getGameList(page);
    }

}
