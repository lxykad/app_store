package com.lxy.shop.ui.download;

import android.support.v4.app.Fragment;

import com.blankj.utilcode.util.LogUtils;
import com.lxy.shop.R;
import com.lxy.shop.common.base.BaseActivity;
import com.lxy.shop.databinding.ActivityDownLoadManagerBinding;
import com.lxy.shop.di.component.AppComponent;
import com.lxy.shop.ui.download.downloaded.DownLoadedFragment;
import com.lxy.shop.ui.download.downloading.DownLoadingFragment;

import java.util.ArrayList;

/**
 * Created by lxy on 2017/6/30.
 */

public class DownLoadManagerActivity extends BaseActivity {

    private final String[] mTitles = {"下载中", "已完成"};
    private ArrayList<Fragment> mFragments;
    private ActivityDownLoadManagerBinding mBinding;

    @Override
    protected void setActivityComponent(AppComponent appComponent) {

    }

    @Override
    protected void onCreate() {

        mBinding = (ActivityDownLoadManagerBinding) mChildBinding;
        initData();

    }

    private void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(new DownLoadingFragment());
        mFragments.add(new DownLoadedFragment());

        mBinding.slidingTabLayout.setViewPager(mBinding.viewPager, mTitles, this, mFragments);
    }

    @Override
    protected int setLayoutId() {

        return R.layout.activity_down_load_manager;
    }

}
