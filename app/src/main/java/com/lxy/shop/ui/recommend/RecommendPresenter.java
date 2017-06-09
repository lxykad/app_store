package com.lxy.shop.ui.recommend;

import com.lxy.shop.common.base.BasePresenter;
import com.lxy.shop.common.rx.PageBean;
import com.lxy.shop.common.rx.RxErrorHandler;
import com.lxy.shop.common.rx.RxHttpResponse;
import com.lxy.shop.common.rx.observer.ErrorHandObserver;
import com.lxy.shop.common.rx.observer.ProgressObserver;
import com.lxy.shop.ui.recommend.contract.RecommendContract;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by lxy on 2017/5/11.
 */

public class RecommendPresenter extends BasePresenter<RecommendModel, RecommendContract.View> {


    @Inject
    public RecommendPresenter(RecommendModel mModel, RecommendContract.View mView) {
        super(mModel, mView);
    }

    public void getRecommendData() {

        mModel.getApps()
                .compose(RxHttpResponse.<PageBean<AppBean>>handResult())
                .subscribe(new ProgressObserver<PageBean<AppBean>>(mContext, mView) {
                    @Override
                    public void onNext(PageBean<AppBean> beanPageBean) {
                        mView.showResust(beanPageBean);
                    }
                });

    }

}
