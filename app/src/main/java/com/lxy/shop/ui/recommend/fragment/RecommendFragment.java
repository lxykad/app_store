package com.lxy.shop.ui.recommend.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lxy.shop.R;
import com.lxy.shop.common.base.BaseFragment;
import com.lxy.shop.common.rx.PageBean;
import com.lxy.shop.databinding.FragmentRecommendBinding;
import com.lxy.shop.di.component.AppComponent;
import com.lxy.shop.di.component.DaggerFragmentComponent;
import com.lxy.shop.di.module.FragmentModule;
import com.lxy.shop.ui.recommend.AppBean;
import com.lxy.shop.ui.recommend.RecommendPresenter;
import com.lxy.shop.ui.recommend.adapter.RecommendAdapter;
import com.lxy.shop.ui.recommend.contract.RecommendContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxy on 2017/6/8.
 */

public class RecommendFragment extends BaseFragment<RecommendPresenter> implements RecommendContract.View {

    private FragmentRecommendBinding mBinding;
    private RecommendAdapter mAdapter;
    private List<AppBean> mList;

    @Override
    protected void visiableToUser() {

    }

    @Override
    protected void firstVisiableToUser() {
        System.out.println("RecommendFragment======firstVisiableToUser" );
        init();
        LoadData();
    }

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {
        DaggerFragmentComponent.builder().appComponent(appComponent)
                .fragmentModule(new FragmentModule(this)).build().inject(this);
    }

    @Override
    public int getLayoutId() {

        return R.layout.fragment_recommend;
    }

    @Override
    public void initChildBinding() {
        mBinding = (FragmentRecommendBinding) mChildBinding;
        System.out.println("RecommendFragment======initChild" );
    }

    @Override
    public void onEmptyClick(View view) {
        LoadData();
    }


    public void init() {
        mList = new ArrayList<>();
        mAdapter = new RecommendAdapter(R.layout.list_item_recommend_fragment, mList);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                AppBean appBean = mList.get(position);
                Toast.makeText(view.getContext(), appBean.displayName, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void LoadData() {
        mPresenter.getRecommendData();
    }

    @Override
    public void showResust(PageBean<AppBean> bean) {

        mAdapter.addItems(bean.getDatas());
        System.out.println("RecommendFragment======count:" + bean.getDatas().size());
    }

    @Override
    public void showNoData() {

    }

}
