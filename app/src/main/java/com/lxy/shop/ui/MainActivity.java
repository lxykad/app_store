package com.lxy.shop.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.lxy.shop.R;
import com.lxy.shop.common.base.BaseActivity;
import com.lxy.shop.di.component.AppComponent;
import com.lxy.shop.di.component.DaggerActivityComponent;
import com.lxy.shop.di.module.ActivityModule;
import com.lxy.shop.ui.recommend.AndroidBean;
import com.lxy.shop.ui.recommend.RecommendPresenter;
import com.lxy.shop.ui.recommend.contract.RecommendContract;

public class MainActivity extends BaseActivity<RecommendPresenter> implements RecommendContract.View{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    protected void setActivityComponent(AppComponent appComponent) {
        DaggerActivityComponent.builder().appComponent(appComponent).activityModule(new ActivityModule(this)).build().inject(this);
    }

    public void testClick(View view){

        Toast.makeText(this,""+mPresenter,Toast.LENGTH_SHORT).show();
        mPresenter.getAndroidData();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showResust(AndroidBean bean) {

    }

    @Override
    public void showNoData() {

    }

    @Override
    public void showError(String msg) {

    }
}
