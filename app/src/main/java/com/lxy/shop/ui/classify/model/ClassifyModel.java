package com.lxy.shop.ui.classify.model;

import com.lxy.shop.common.rx.BaseBean;
import com.lxy.shop.data.api.ApiService;
import com.lxy.shop.ui.classify.bean.ClassifyBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by lxy on 2017/6/21.
 */


public class ClassifyModel {

    private ApiService mApiService;


    public ClassifyModel(ApiService mApiService) {

        this.mApiService = mApiService;
    }

    public Observable<BaseBean<List<ClassifyBean>>> getData() {

        return mApiService.getCategories();
    }
}
