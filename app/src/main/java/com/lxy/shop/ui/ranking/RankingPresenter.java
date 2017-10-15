package com.lxy.shop.ui.ranking;

import com.lxy.shop.common.base.BasePresenter;
import com.lxy.shop.common.rx.PageBean;
import com.lxy.shop.common.rx.RxHttpResponse;
import com.lxy.shop.common.rx.observer.ErrorHandObserver;
import com.lxy.shop.common.rx.observer.ProgressObserver;
import com.lxy.shop.ui.ranking.contract.RankingContract;
import com.lxy.shop.ui.recommend.AppBean;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by lxy on 2017/6/16.
 */

public class RankingPresenter extends BasePresenter<RankingModel, RankingContract.AppinfoView> {

    @Inject
    public RankingPresenter(RankingModel mModel, RankingContract.AppinfoView mView) {
        super(mModel, mView);
    }

    public void getRankData(int page,boolean isRefresh) {

        Observer observer = null;

        if (page == 0 && !isRefresh) {//第一页

            observer = new ProgressObserver<PageBean<AppBean>>(mContext, mView) {

                @Override
                public void onNext(PageBean<AppBean> beanPageBean) {
                    mView.showResult(beanPageBean);
                }
            };

        } else {//分页
            observer = new ErrorHandObserver<PageBean<AppBean>>(mContext) {

                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(PageBean<AppBean> beanPageBean) {
                    mView.showResult(beanPageBean);
                }

                @Override
                public void onComplete() {
                    mView.onLoadMoreComplete();
                }
            };
        }


        mModel.getData(page)
                .compose(RxHttpResponse.<PageBean<AppBean>>handResult())
                .subscribe(observer);
    }
}
