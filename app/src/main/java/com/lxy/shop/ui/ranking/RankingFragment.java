package com.lxy.shop.ui.ranking;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lxy.shop.R;
import com.lxy.shop.common.base.BaseApplication;
import com.lxy.shop.common.base.BaseFragment;
import com.lxy.shop.common.rx.PageBean;
import com.lxy.shop.databinding.FragmentRankingBinding;
import com.lxy.shop.di.component.AppComponent;
import com.lxy.shop.di.component.DaggerAppInfoComponent;
import com.lxy.shop.di.module.AppInfoMoudle;
import com.lxy.shop.ui.detail.AppDetailActivity;
import com.lxy.shop.ui.ranking.adapter.RankAdapter;
import com.lxy.shop.ui.ranking.contract.RankingContract;
import com.lxy.shop.ui.recommend.AppBean;
import com.lxy.shop.widget.RefreshLayout;
import com.orhanobut.hawk.Hawk;

import org.eclipse.jdt.internal.compiler.env.ISourceField;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lxy on 2017/6/8.
 */

public class RankingFragment extends BaseFragment<RankingPresenter> implements RankingContract.AppinfoView,
        BaseQuickAdapter.RequestLoadMoreListener,
        RefreshLayout.RefreshListener {

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
        mRankBinding.swipRefreshLayout.setRefreshListener(this);
        loadData(false);
    }

    public void loadData(boolean isRefresh) {
        mPresenter.getRankData(mPage, isRefresh);
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
        mAdapter = new RankAdapter(R.layout.list_item_recommend_fragment, mList, getActivity());

        mRankBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRankBinding.recyclerView.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(this, mRankBinding.recyclerView);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                BaseApplication.getInstance().setView(view);
                AppBean appBean = mList.get(position);
                AppDetailActivity.startActivity(getContext(), appBean);
            }
        });

        /*mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                showToast(""+view.getClass().getSimpleName());
            }
        });*/
    }

    @Override
    public void onEmptyClick(View view) {
        loadData(false);
    }


    @Override
    public void showResult(PageBean<AppBean> pageBean) {

        List<AppBean> list = pageBean.getDatas();

        if (mPage == 0) {
            mList.clear();
        }

        mAdapter.addData(list);
        if (pageBean.isHasMore()) {
            mPage++;
        }
        mAdapter.setEnableLoadMore(pageBean.isHasMore());
        mRankBinding.swipRefreshLayout.dismissRefreshing();
        showToast(mList.size() + "");

    }

    @Override
    public void onLoadMoreComplete() {
        mAdapter.loadMoreComplete();
        LogUtils.d("load_more===========onLoadMoreComplete");
    }

    @Override
    public void onLoadMoreRequested() {
        LogUtils.d("load_more===========");
        mRankBinding.swipRefreshLayout.dismissRefreshing();
        loadData(false);
    }

    @Override
    public void refreshData() {
        LogUtils.d("refresh===========onRefresh");
        mPage = 0;
        loadData(true);
    }
}
