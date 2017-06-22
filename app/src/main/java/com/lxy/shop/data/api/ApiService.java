package com.lxy.shop.data.api;

import com.lxy.shop.common.rx.BaseBean;
import com.lxy.shop.common.rx.PageBean;
import com.lxy.shop.ui.classify.bean.ClassifyBean;
import com.lxy.shop.ui.login.bean.LoginBean;
import com.lxy.shop.ui.login.bean.LoginRequestBean;
import com.lxy.shop.ui.recommend.AndroidBean;
import com.lxy.shop.ui.recommend.AppBean;
import com.lxy.shop.ui.recommend.bean.RecommendBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lxy on 2017/5/11.
 */
public interface ApiService {

    public static final String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";

    @GET("{type}/{count}/{page}")
    public Observable<AndroidBean> getAndroid(@Path("type") String type, @Path("count") int count, @Path("page") int page);

    //推荐 -- test
    @GET("featured2")
    public Observable<BaseBean<PageBean<AppBean>>> getRecommendApps(@Query("p") String param);


    //推荐 --- banner
    @GET("index")
    public Observable<BaseBean<RecommendBean>> getRecommendData();

    // 排行
    @GET("toplist")
    public Observable<BaseBean<PageBean<AppBean>>> getRankingList(@Query("page") int page);

    //游戏
    @GET("game")
    public Observable<BaseBean<PageBean<AppBean>>> getGameList(@Query("page") int page);

    //分类
    @GET("category")
    Observable<BaseBean<List<ClassifyBean>>> getCategories();

    //登录
    @POST("login")
    Observable<BaseBean<LoginBean>> login(@Body LoginRequestBean param);
}
