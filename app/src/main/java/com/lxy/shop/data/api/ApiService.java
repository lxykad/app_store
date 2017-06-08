package com.lxy.shop.data.api;

import com.lxy.shop.common.rx.BaseBean;
import com.lxy.shop.common.rx.PageBean;
import com.lxy.shop.ui.recommend.AndroidBean;
import com.lxy.shop.ui.recommend.AppBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lxy on 2017/5/11.
 */
public interface ApiService {

    public static final String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";

    //推荐
    @GET("featured2")
    public Observable<BaseBean<PageBean<AppBean>>> getRecommendApps(@Query("p") String param);

    @GET("{type}/{count}/{page}")
    public Observable<AndroidBean> getAndroid(@Path("type") String type, @Path("count") int count, @Path("page") int page);


}
