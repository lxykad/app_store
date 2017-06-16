package com.lxy.shop.ui.ranking;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lxy.shop.R;
import com.lxy.shop.common.base.BaseFragment;
import com.lxy.shop.common.base.BaseView;
import com.lxy.shop.common.rx.PageBean;
import com.lxy.shop.databinding.FragmentRankingBinding;
import com.lxy.shop.di.component.AppComponent;
import com.lxy.shop.di.component.DaggerAppInfoComponent;
import com.lxy.shop.di.component.DaggerFragmentComponent;
import com.lxy.shop.di.module.AppInfoMoudle;
import com.lxy.shop.di.module.FragmentModule;
import com.lxy.shop.ui.ranking.adapter.RankAdapter;
import com.lxy.shop.ui.ranking.contract.RankingContract;
import com.lxy.shop.ui.recommend.AppBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxy on 2017/6/8.
 */

public class RankingFragment extends BaseFragment<RankingPresenter> implements RankingContract.AppinfoView {

    private FragmentRankingBinding mRankBinding;
    private RankAdapter mAdapter;
    private List<AppBean> mList;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    protected void visiableToUser() {

    }

    @Override
    protected void firstVisiableToUser() {
        //
        loadData();
    }

    public void loadData() {
        mPresenter.getRankData(0);
    }

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {
        DaggerAppInfoComponent.builder().appComponent(appComponent)
                .appInfoMoudle(new AppInfoMoudle(this))
                .build().injectRankFragment(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_ranking;
    }

    @Override
    public void initChildBinding() {
        mRankBinding = (FragmentRankingBinding) mChildBinding;
        mList = new ArrayList<>();
        mAdapter = new RankAdapter(R.layout.list_item_recommend_fragment,mList);

        mRankBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRankBinding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onEmptyClick(View view) {

    }


    @Override
    public void showResult(PageBean<AppBean> page) {
        List<AppBean> list = page.getDatas();

        mList.clear();
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMoreComplete() {

    }
}
