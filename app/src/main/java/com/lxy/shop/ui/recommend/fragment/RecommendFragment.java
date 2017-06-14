package com.lxy.shop.ui.recommend.fragment;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.lxy.shop.R;
import com.lxy.shop.common.base.BaseFragment;
import com.lxy.shop.databinding.FragmentRecommendBinding;
import com.lxy.shop.di.component.AppComponent;
import com.lxy.shop.di.component.DaggerFragmentComponent;
import com.lxy.shop.di.module.FragmentModule;
import com.lxy.shop.ui.recommend.AppBean;
import com.lxy.shop.ui.recommend.RecommendPresenter;
import com.lxy.shop.ui.recommend.adapter.RecommendAdapter;
import com.lxy.shop.ui.recommend.bean.RecommendBean;
import com.lxy.shop.ui.recommend.contract.RecommendContract;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lxy on 2017/6/8.
 */

public class RecommendFragment extends BaseFragment<RecommendPresenter> implements RecommendContract.View {

    private static final int TYPE_BANNER = 1;
    private static final int TYPE_ICON = 2;
    private static final int TYPE_APP_TOP = 3;
    private static final int TYPE_GAME_TOP = 4;

    private FragmentRecommendBinding mBinding;
    private List<AppBean> mAppList;
    private List<AppBean> mGameList;
    private DelegateAdapter mDelegateAdapter;

    @Override
    protected void visiableToUser() {

    }

    @Override
    protected void firstVisiableToUser() {
        System.out.println("RecommendFragment======firstVisiableToUser");
       // init(1,1);
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
        System.out.println("RecommendFragment======initChild");
    }

    @Override
    public void onEmptyClick(View view) {
        LoadData();
    }


    public void init(int bannerCount, int iconCount) {

        if (mAppList == null) {
            mAppList = new ArrayList<>();
        }
        if (mGameList == null) {
            mGameList = new ArrayList<>();
        }

        //设置layoutmanager
        final VirtualLayoutManager layoutManager = new VirtualLayoutManager(getContext());
        mBinding.recyclerView.setLayoutManager(layoutManager);

        mDelegateAdapter = new DelegateAdapter(layoutManager, true);
        mBinding.recyclerView.setAdapter(mDelegateAdapter);


        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();

        //banner
        if (bannerCount > 0) {

            //设置复用池的大小
            RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool();
            mBinding.recyclerView.setRecycledViewPool(pool);
            pool.setMaxRecycledViews(0,10);

            adapters.add(new RecommendAdapter(getContext(), new LinearLayoutHelper(), 1) {
                @Override
                public void onViewRecycled(RecommendViewHolder holder) {
                    super.onViewRecycled(holder);
                }

                @Override
                public RecommendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                    if (viewType == TYPE_BANNER) {

                        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.list_item_recommend_banner, parent, false);
                        return new RecommendViewHolder(inflate);
                    }

                    return super.onCreateViewHolder(parent, viewType);
                }

                @Override
                public int getItemViewType(int position) {
                    return TYPE_BANNER;
                }

                @Override
                public void onBindViewHolder(RecommendViewHolder holder, int position) {
                    // super.onBindViewHolder(holder, position);
                }
            });
        }

        // icon
        if (iconCount > 0) {

            //设置复用池的大小
            RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool();
            mBinding.recyclerView.setRecycledViewPool(pool);
            pool.setMaxRecycledViews(0,10);

            adapters.add(new RecommendAdapter(getContext(), new LinearLayoutHelper(), 1) {
                @Override
                public void onViewRecycled(RecommendViewHolder holder) {
                    super.onViewRecycled(holder);
                }

                @Override
                public RecommendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                    if (viewType == TYPE_ICON) {

                        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.list_item_recommend_icon, parent, false);
                        return new RecommendViewHolder(inflate);
                    }

                    return super.onCreateViewHolder(parent, viewType);
                }

                @Override
                public int getItemViewType(int position) {
                    return TYPE_ICON;
                }

                @Override
                public void onBindViewHolder(RecommendViewHolder holder, int position) {
                    TextView tvApp = (TextView) holder.itemView.findViewById(R.id.tv_hot_app);
                    tvApp.setText("app");
                    tvApp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(v.getContext(), "app", Toast.LENGTH_SHORT);
                        }
                    });

                    holder.itemView.findViewById(R.id.tv_hot_game).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(v.getContext(), "game", Toast.LENGTH_SHORT);
                        }
                    });

                    holder.itemView.findViewById(R.id.tv_hot_theme).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(v.getContext(), "theme", Toast.LENGTH_SHORT);
                        }
                    });

                }
            });
        }

        //app top
        if (mAppList.size()>0) {
            StickyLayoutHelper helper = new StickyLayoutHelper();
            helper.setBgColor(Color.parseColor("#ffffff"));
            adapters.add(new RecommendAdapter(getContext(),helper,1){
                @Override
                public RecommendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                    if (viewType == TYPE_APP_TOP) {

                        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.list_item_recommend_apps_top, parent, false);
                        return new RecommendViewHolder(inflate);
                    }

                    return super.onCreateViewHolder(parent, viewType);
                }

                @Override
                public int getItemViewType(int position) {
                    return TYPE_APP_TOP;
                }

                @Override
                public void onBindViewHolder(RecommendViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);

                }
            });
        }

        // 推荐应用
        if (mAppList.size() > 0) {

            //设置复用池的大小
            RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool();
            mBinding.recyclerView.setRecycledViewPool(pool);
            pool.setMaxRecycledViews(0,10);

            LinearLayoutHelper layoutHelper1 = new LinearLayoutHelper();
            layoutHelper1.setAspectRatio(2.0f);
            LinearLayoutHelper layoutHelper2 = new LinearLayoutHelper();
            layoutHelper2.setAspectRatio(4.0f);
            layoutHelper2.setDividerHeight(10);
            layoutHelper2.setMargin(10, 30, 10, 10);
            layoutHelper2.setPadding(10, 30, 10, 10);
            layoutHelper2.setBgColor(0xFFF5A623);
            adapters.add(new RecommendAdapter(getContext(), layoutHelper1, 0));
            adapters.add(new RecommendAdapter(getContext(), layoutHelper2, mAppList.size()) {
                @Override
                public void onBindViewHolder(RecommendViewHolder holder, int position) {
                    AppBean appBean = mAppList.get(position);
                    TextView textView = (TextView) holder.itemView.findViewById(R.id.text_title);
                    textView.setText(appBean.displayName);
                    /*if (position % 2 == 0) {
                        VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
                        layoutParams.mAspectRatio = 5;
                        holder.itemView.setLayoutParams(layoutParams);
                    }*/
                }
            });

        }

        //game top
        if (mAppList.size()>0) {

            StickyLayoutHelper helper = new StickyLayoutHelper();
            helper.setBgColor(Color.parseColor("#33ffff"));

            adapters.add(new RecommendAdapter(getContext(),helper,1){
                @Override
                public RecommendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                    if (viewType == TYPE_GAME_TOP) {

                        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.list_item_recommend_games_top, parent, false);
                        return new RecommendViewHolder(inflate);
                    }

                    return super.onCreateViewHolder(parent, viewType);
                }

                @Override
                public int getItemViewType(int position) {
                    return TYPE_GAME_TOP;
                }

                @Override
                public void onBindViewHolder(RecommendViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);

                }
            });
        }

        //推荐游戏
        if (mGameList.size() > 0) {
            LinearLayoutHelper helper = new LinearLayoutHelper();
            helper.setAspectRatio(4.0f);
            helper.setDividerHeight(10);
            helper.setMargin(10, 30, 10, 10);
            helper.setPadding(10, 30, 10, 10);
            helper.setBgColor(0xFFF5A623);

            adapters.add(new RecommendAdapter(getContext(),helper,mGameList.size()){
                @Override
                public void onBindViewHolder(RecommendViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);

                    AppBean appBean = mGameList.get(position);
                    TextView textView = (TextView) holder.itemView.findViewById(R.id.text_title);
                    textView.setText(appBean.displayName);
                }
            });
        }

        mDelegateAdapter.setAdapters(adapters);

        setListenerToRootView();

    }

    public void setListenerToRootView() {

    }

    public void LoadData() {
        mPresenter.getRecommendData();
    }

    @Override
    public void showResust(RecommendBean bean) {

        // mAdapter.addItems(bean.getRecommendApps());

        if (mAppList == null) {
            mAppList = new ArrayList<>();
        }
        if (mGameList == null) {
            mGameList = new ArrayList<>();
        }

        mAppList.clear();
        mAppList = bean.getRecommendApps();

        mGameList.clear();
        mGameList = bean.getRecommendGames();
        init(bean.getBanners().size(), 1);

        //mBinding.recyclerView.getAdapter().notifyDataSetChanged();
        System.out.println("RecommendFragment======app:" + bean.getRecommendApps().size());
        System.out.println("RecommendFragment======game:" + bean.getRecommendGames().size());
    }

    @Override
    public void showNoData() {

    }

}
