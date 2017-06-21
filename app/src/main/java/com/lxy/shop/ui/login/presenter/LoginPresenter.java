package com.lxy.shop.ui.login.presenter;

import com.lxy.shop.common.base.BasePresenter;
import com.lxy.shop.common.rx.RxHttpResponse;
import com.lxy.shop.common.rx.observer.ErrorHandObserver;
import com.lxy.shop.ui.login.bean.LoginBean;
import com.lxy.shop.ui.login.contract.LoginContract;
import com.lxy.shop.ui.login.model.LoginModel;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by lxy on 2017/6/20.
 */

public class LoginPresenter extends BasePresenter<LoginModel,LoginContract.LoginView> {

    @Inject
    public LoginPresenter(LoginModel mModel, LoginContract.LoginView mView) {
        super(mModel, mView);
    }

    public void login(String phone, String pwd){

        mModel.login(phone,pwd)
                .compose(RxHttpResponse.<LoginBean>handResult())
                .subscribe(new ErrorHandObserver<LoginBean>(mContext) {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mView.showLoading();
                    }

                    @Override
                    public void onNext(LoginBean bean) {
                        mView.loginSuccess(bean);
                    }

                    @Override
                    public void onComplete() {
                        mView.dismissLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.dismissLoading();
                    }
                });
    }
}
