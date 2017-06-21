package com.lxy.shop.ui.ranking;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lxy.shop.R;
import com.lxy.shop.common.base.BaseFragment;
import com.lxy.shop.common.rx.PageBean;
import com.lxy.shop.databinding.FragmentRankingBinding;
import com.lxy.shop.di.component.AppComponent;
import com.lxy.shop.di.component.DaggerAppInfoComponent;
import com.lxy.shop.di.module.AppInfoMoudle;
import com.lxy.shop.ui.ranking.adapter.RankAdapter;
import com.lxy.shop.ui.ranking.contract.RankingContract;
import com.lxy.shop.ui.recommend.AppBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lxy on 2017/6/8.
 */

public class RankingFragment extends BaseFragment<RankingPresenter> implements RankingContract.AppinfoView, BaseQuickAdapter.RequestLoadMoreListener,SwipeRefreshLayout.OnRefreshListener {

    private FragmentRankingBinding mRankBinding;
    private RankAdapter mAdapter;
    private List<AppBean> mList;
    private int mPage;


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
        mPresenter.getRankData(mPage);
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
        mAdapter = new RankAdapter(R.layout.list_item_recommend_fragment, mList);

        mRankBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRankBinding.recyclerView.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(this, mRankBinding.recyclerView);

        //mRankBinding.swipRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onEmptyClick(View view) {
        loadData();
    }


    @Override
    public void showResult(PageBean<AppBean> pageBean) {

        List<AppBean> list = pageBean.getDatas();

        if (mPage==0) {

        }

        mAdapter.addData(list);
        if (pageBean.isHasMore()) {
            mPage++;
        }
        mAdapter.setEnableLoadMore(pageBean.isHasMore());

    }

    @Override
    public void onLoadMoreComplete() {
        mAdapter.loadMoreComplete();
        System.out.println("load_more===========onLoadMoreComplete");
    }

    @Override
    public void onLoadMoreRequested() {
        System.out.println("load_more===========");
        loadData();
    }

    @Override
    public void onRefresh() {
        System.out.println("load_more===========onRefresh");
        mPage = 0;
        loadData();
    }
}
