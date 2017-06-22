package com.lxy.shop.ui.detail;

import android.content.Context;
import android.content.Intent;

import com.lxy.shop.R;
import com.lxy.shop.common.base.BaseActivity;
import com.lxy.shop.di.component.AppComponent;
import com.lxy.shop.ui.detail.presenter.AppDetailPresenter;

/**
 * Created by lxy on 2017/6/22.
 */

public class AppDetailActivity extends BaseActivity<AppDetailPresenter> {


    public static void startActivity(Context context){

        Intent intent = new Intent(context,AppDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void setActivityComponent(AppComponent appComponent) {

    }

    @Override
    protected void onCreate() {

    }

    @Override
    protected int setLayoutId() {

        return R.layout.activity_app_detail;
    }
}
