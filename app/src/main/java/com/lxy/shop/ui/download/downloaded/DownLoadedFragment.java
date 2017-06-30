package com.lxy.shop.ui.download.downloaded;

import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.lxy.shop.R;
import com.lxy.shop.common.base.BaseFragment;
import com.lxy.shop.di.component.AppComponent;

/**
 * Created by lxy on 2017/6/30.
 */

public class DownLoadedFragment extends BaseFragment {

    @Override
    protected void visiableToUser() {

    }

    @Override
    protected void firstVisiableToUser() {
        LogUtils.d("download=======loaded");
    }

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_downloaded;
    }

    @Override
    public void initChildBinding() {

    }

    @Override
    public void onEmptyClick(View view) {

    }


}
