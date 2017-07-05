package com.lxy.shop.ui.download.downloaded;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.lxy.shop.R;
import com.lxy.shop.common.base.BaseFragment;
import com.lxy.shop.databinding.FragmentDownloadedBinding;
import com.lxy.shop.di.component.AppComponent;
import com.lxy.shop.ui.download.downloaded.adapter.DownLoadedAdapter;

import java.util.ArrayList;

import zlc.season.rxdownload2.entity.DownloadEvent;
import zlc.season.rxdownload2.entity.DownloadFlag;
import zlc.season.rxdownload2.entity.DownloadRecord;
import zlc.season.rxdownload2.entity.DownloadStatus;

/**
 * Created by lxy on 2017/6/30.
 */

public class DownLoadedFragment extends BaseFragment {

    private FragmentDownloadedBinding mBinding;
    private DownLoadedAdapter mAdapter;
    private ArrayList<DownloadRecord> mList;

    @Override
    protected void visiableToUser() {

    }

    @Override
    protected void firstVisiableToUser() {
        LogUtils.d("download=======loaded");
        initData();
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
        mBinding = (FragmentDownloadedBinding) mChildBinding;
    }

    @Override
    public void onEmptyClick(View view) {

    }


    public void initData() {

        mList = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            DownloadRecord record = new DownloadRecord();
            record.setId(i);

            if (i == 0) {
                record.setFlag(DownloadFlag.NORMAL);
            }

            mList.add(record);

        }

        mAdapter = new DownLoadedAdapter(R.layout.list_item_down_loaded, mList);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.recyclerView.setAdapter(mAdapter);
    }

}
