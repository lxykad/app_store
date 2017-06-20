package com.lxy.shop.ui.game;

import com.lxy.shop.common.base.BasePresenter;
import com.lxy.shop.common.rx.BaseBean;
import com.lxy.shop.common.rx.PageBean;
import com.lxy.shop.common.rx.RxHttpResponse;
import com.lxy.shop.common.rx.observer.ErrorHandObserver;
import com.lxy.shop.common.rx.observer.ProgressObserver;
import com.lxy.shop.data.api.ApiService;
import com.lxy.shop.ui.game.constract.GameConstract;
import com.lxy.shop.ui.recommend.AppBean;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by lxy on 2017/6/20.
 */

public class GamePresenter extends BasePresenter<GameModel,GameConstract.AppinfoView> {



    @Inject
    public GamePresenter(GameModel mModel, GameConstract.AppinfoView mView) {
        super(mModel, mView);
    }

    public void getGameList(int page){

        Observer observer = null;

        if (page == 0) {//第一页

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
