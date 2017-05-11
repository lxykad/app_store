package com.lxy.shop.ui.recommend;

import android.widget.Toast;

import com.lxy.shop.common.base.BasePresenter;
import com.lxy.shop.ui.recommend.contract.RecommendContract;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lxy on 2017/5/11.
 */

public class RecommendPresenter extends BasePresenter<RecommendModel,RecommendContract.View> {

    @Inject
    public RecommendPresenter(RecommendModel mModel, RecommendContract.View mView) {
        super(mModel, mView);
    }

    public void getAndroidData(){

        mModel.getApps()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AndroidBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("list======err==="+e.toString());
                    }

                    @Override
                    public void onNext(AndroidBean bean) {
                        System.out.println("list======"+bean.getResults().size());
                    }
                });

    }

}
