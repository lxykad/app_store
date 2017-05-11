package com.lxy.shop.data.api;

import com.lxy.shop.ui.recommend.AndroidBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lxy on 2017/5/11.
 */

public interface ApiService {


    @GET("featured")
    public void getRecommendApps(@Query("p") String param);

    @GET("Android")
    public Observable<AndroidBean> getAndroid(@Query("page") String page);


}
