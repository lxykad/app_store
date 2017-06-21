package com.lxy.shop.ui.game;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lxy.shop.R;
import com.lxy.shop.common.base.BaseFragment;
import com.lxy.shop.common.rx.PageBean;
import com.lxy.shop.databinding.FragmentGameBinding;
import com.lxy.shop.di.component.AppComponent;
import com.lxy.shop.di.component.DaggerGameComponent;
import com.lxy.shop.di.module.GameMoudle;
import com.lxy.shop.ui.game.adapter.GameAdapter;
import com.lxy.shop.ui.game.constract.GameConstract;
import com.lxy.shop.ui.recommend.AppBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxy on 2017/6/8.
 */

public class GameFragment extends BaseFragment<GamePresenter> implements GameConstract.AppinfoView ,BaseQuickAdapter.RequestLoadMoreListener{

    private FragmentGameBinding mBinding;
    private GameAdapter mAdapter;
    private List<AppBean> mList;
    private int mPage;

    @Override
    protected void visiableToUser() {

    }

    @Override
    protected void firstVisiableToUser() {
        loadData();
    }

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {

        DaggerGameComponent.builder()
                .appComponent(appComponent)
                .gameMoudle(new GameMoudle(this))
                .build()
                .injectGameFragment(this);

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_game;
    }

    @Override
    public void initChildBinding() {

        mBinding = (FragmentGameBinding) mChildBinding;

        mList = new ArrayList<>();
        mAdapter = new GameAdapter(R.layout.list_item_recommend_fragment, mList);

        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onEmptyClick(View view) {
        loadData();
    }

    public void loadData() {
        mPresenter.getGameList(mPage);
    }

    @Override
    public void showResult(PageBean<AppBean> pageBean) {
        mList = pageBean.getDatas();
        mAdapter.addData(mList);

        if (pageBean.isHasMore()) {
            mPage++;
        }

        mAdapter.setEnableLoadMore(pageBean.isHasMore());
        mAdapter.setOnLoadMoreListener(this, mBinding.recyclerView);

    }

    @Override
    public void onLoadMoreComplete() {
        mAdapter.loadMoreComplete();
    }

    @Override
    public void onLoadMoreRequested() {

        loadData();
    }
}
