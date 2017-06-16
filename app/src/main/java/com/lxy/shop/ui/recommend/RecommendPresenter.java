package com.lxy.shop.ui.recommend;

import android.Manifest;
import android.app.Activity;

import com.lxy.shop.common.base.BasePresenter;
import com.lxy.shop.common.rx.PageBean;
import com.lxy.shop.common.rx.RxHttpResponse;
import com.lxy.shop.common.rx.observer.ProgressObserver;
import com.lxy.shop.ui.recommend.bean.RecommendBean;
import com.lxy.shop.ui.recommend.contract.RecommendContract;
import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by lxy on 2017/5/11.
 */

public class RecommendPresenter extends BasePresenter<RecommendModel, RecommendContract.View> {


    @Inject
    public RecommendPresenter(RecommendModel mModel, RecommendContract.View mView) {
        super(mModel, mView);
    }

    public void getRecommendData() {

        RxPermissions rxPermissions = new RxPermissions((Activity) mContext);
        rxPermissions.request(Manifest.permission.READ_PHONE_STATE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean aBoolean) throws Exception {

                        if (aBoolean) {
                            
                            mModel.getRecommend()
                                    .compose(RxHttpResponse.<RecommendBean>handResult())
                                    .subscribe(new ProgressObserver<RecommendBean>(mContext, mView) {
                                        @Override
                                        public void onNext(RecommendBean recommendBean) {
                                            mView.showResust(recommendBean);
                                        }
                                    });

                        } else {
                            System.out.println("RecommendPresenter=======拒绝");
                        }
                    }
                });

    }

}
