package com.lxy.shop.ui.login;

import android.graphics.Color;

import com.blankj.utilcode.util.CacheUtils;
import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.lxy.shop.R;
import com.lxy.shop.common.base.BaseActivity;
import com.lxy.shop.common.constant.Constant;
import com.lxy.shop.databinding.ActivityLoginBinding;
import com.lxy.shop.di.component.AppComponent;
import com.lxy.shop.di.component.DaggerLoginComponent;
import com.lxy.shop.di.module.LoginModule;
import com.lxy.shop.ui.login.bean.LoginBean;
import com.lxy.shop.ui.login.contract.LoginContract;
import com.lxy.shop.ui.login.presenter.LoginPresenter;
import com.lxy.shop.util.Tool;
import com.orhanobut.hawk.Hawk;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.LoginView {

    private ActivityLoginBinding mBinding;

    @Override
    protected void setActivityComponent(AppComponent appComponent) {
        DaggerLoginComponent.builder().appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void onCreate() {
        mBinding = (ActivityLoginBinding) mChildBinding;
        mBinding.titleLayout.setTitleText("登录");
        initEvents();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String msg) {
    }

    @Override
    public void dismissLoading() {
    }

    @Override
    public void loginSuccess(LoginBean bean) {

        //CacheUtils.getInstance().put(Constant.USER, bean.user);
        //CacheUtils.getInstance().put(Constant.TOKEN, bean.token);
        Hawk.put(Constant.USER,bean.user);
        Hawk.put(Constant.TOKEN,bean.token);

        EventBus.getDefault().post(bean.user);
        super.finish();
    }

    public void initEvents() {

        Observable<CharSequence> obPhone = RxTextView.textChanges(mBinding.etPhone);
        Observable<CharSequence> obPwd = RxTextView.textChanges(mBinding.etPwd);

        Observable.combineLatest(obPhone, obPwd, new BiFunction<CharSequence, CharSequence, Boolean>() {

            @Override
            public Boolean apply(@NonNull CharSequence phone, @NonNull CharSequence pwd) throws Exception {

                return Tool.checkMobile(phone.toString().trim()) && pwd.toString().toString().length() > 5;
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(@NonNull Boolean b) throws Exception {

                RxView.enabled(mBinding.btLogin).accept(b);

                if (b) {
                    mBinding.btLogin.setBackgroundColor(Color.parseColor("#39c6c1"));

                } else {
                    mBinding.btLogin.setBackgroundColor(Color.parseColor("#d1d1d1"));
                }
            }
        });

        RxView.clicks(mBinding.btLogin)
                .subscribe(new Consumer<Object>() {

                    @Override
                    public void accept(@NonNull Object o) throws Exception {

                        mPresenter.login(mBinding.etPhone.getText().toString().trim(), mBinding.etPwd.getText().toString().trim());
                    }
                });
    }

}

