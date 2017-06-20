package com.lxy.shop.common.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.lxy.shop.di.component.AppComponent;

import javax.inject.Inject;

/**
 * Created by lxy on 2017/5/11.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity{

    @Inject
    public T mPresenter ;

    protected ViewDataBinding mChildBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mChildBinding = DataBindingUtil.setContentView(this,setLayoutId());

        setActivityComponent(BaseApplication.getInstance().getAppComponent());

        onCreate();

    }

    protected void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    protected abstract void setActivityComponent(AppComponent appComponent);
    protected abstract void onCreate();
    protected abstract int setLayoutId();
}
