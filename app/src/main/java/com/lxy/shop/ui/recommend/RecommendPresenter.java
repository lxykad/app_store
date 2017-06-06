package com.lxy.shop.ui.recommend;

import com.lxy.shop.common.base.BasePresenter;
import com.lxy.shop.common.rx.PageBean;
import com.lxy.shop.common.rx.RxHttpResponse;
import com.lxy.shop.ui.recommend.contract.RecommendContract;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by lxy on 2017/5/11.
 */

public class RecommendPresenter extends BasePresenter<RecommendModel, RecommendContract.View> {

    @Inject
    public RecommendPresenter(RecommendModel mModel, RecommendContract.View mView) {
        super(mModel, mView);
    }

    public void getAndroidData() {

        mModel.getApps()
                .compose(RxHttpResponse.<PageBean<AppBean>>handResult())
                .subscribe(new Subscriber<PageBean<AppBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("RecommendPresenter=====err:" + e.toString());
                    }

                    @Override
                    public void onNext(PageBean<AppBean> pageBean) {
                        System.out.println("RecommendPresenter=====suc:" + pageBean.getDatas().size());
                    }
                });


    }

}
