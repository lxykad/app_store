package com.lxy.shop.ui.download.downloading;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.lxy.shop.R;
import com.lxy.shop.common.base.BaseFragment;
import com.lxy.shop.databinding.FragmentDownloadedBinding;
import com.lxy.shop.databinding.FragmentDownloadingBinding;
import com.lxy.shop.di.component.AppComponent;
import com.lxy.shop.di.component.DaggerDownloadedComponent;
import com.lxy.shop.di.module.DownloadModule;
import com.lxy.shop.di.module.DownloadRecordMoudle;
import com.lxy.shop.ui.download.Presenter.DownloadRecordPresenter;
import com.lxy.shop.ui.download.adapter.DownloadAdapter;
import com.lxy.shop.ui.download.constract.DownloadConstract;
import com.lxy.shop.ui.recommend.AppBean;

import java.util.ArrayList;
import java.util.List;

import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadRecord;

/**
 * Created by lxy on 2017/6/30.
 */

public class DownLoadingFragment extends BaseFragment<DownloadRecordPresenter> implements DownloadConstract.AppManagerView {

    private List<DownloadRecord> mList;
    private FragmentDownloadingBinding mBinding;
    private DownloadAdapter mAdapter;

    @Override
    protected void visiableToUser() {

    }

    @Override
    protected void firstVisiableToUser() {

        mPresenter.getRecords();
    }

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {

        DaggerDownloadedComponent.builder().appComponent(appComponent).downloadRecordMoudle(new DownloadRecordMoudle(this))
                .build()
                .inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_downloading;
    }

    @Override
    public void initChildBinding() {
        mBinding = (FragmentDownloadingBinding) mChildBinding;

        mList = new ArrayList<>();
        mAdapter = new DownloadAdapter(R.layout.list_item_recommend_fragment, mList, RxDownload.getInstance(getContext()), getActivity());

        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onEmptyClick(View view) {

    }

    @Override
    public void showDownloading(List<DownloadRecord> list) {

        LogUtils.d("downloading=======size====" + list.size());
        mAdapter.addData(list);
    }

    @Override
    public void showUpdateApps(List<AppBean> appInfos) {

    }
}
