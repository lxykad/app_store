package com.lxy.shop.ui.recommend.fragment;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.bumptech.glide.Glide;
import com.lxy.shop.R;
import com.lxy.shop.common.base.BaseApplication;
import com.lxy.shop.common.base.BaseFragment;
import com.lxy.shop.databinding.FragmentRecommendBinding;
import com.lxy.shop.di.component.AppComponent;
import com.lxy.shop.di.component.DaggerFragmentComponent;
import com.lxy.shop.di.module.FragmentModule;
import com.lxy.shop.ui.detail.AppDetailActivity;
import com.lxy.shop.ui.recommend.AppBean;
import com.lxy.shop.ui.recommend.RecommendPresenter;
import com.lxy.shop.ui.recommend.adapter.RecommendAdapter;
import com.lxy.shop.ui.recommend.bean.RecommendBean;
import com.lxy.shop.ui.recommend.contract.RecommendContract;
import com.lxy.shop.widget.BannerImageLoader;
import com.youth.banner.Banner;

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
    private static final int TYPE_APP_LIST = 5;
    private static final int TYPE_GAME_LIST = 6;
    private String baseImgUrl = "http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";

    private FragmentRecommendBinding mBinding;
    private List<AppBean> mAppList;
    private List<AppBean> mGameList;
    private DelegateAdapter mDelegateAdapter;
    private List<DelegateAdapter.Adapter> mDelegateAdapters;
    private RecommendBean mRecommendBean;

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


        mDelegateAdapters = new LinkedList<>();

        //banner
        if (bannerCount > 0) {

            //设置复用池的大小
            RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool();
            mBinding.recyclerView.setRecycledViewPool(pool);
            pool.setMaxRecycledViews(0, 10);

            LinearLayoutHelper helper = new LinearLayoutHelper();
            helper.setItemCount(1);// 设置布局里Item个数
            // helper.setPadding(0, 10, 0, 10);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
            // helper.setMargin(0, 1, 0, 1);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
            helper.setBgColor(Color.GRAY);// 设置背景颜色
            helper.setAspectRatio(2.5f);// 设置设置布局内每行布局的宽与高的比

            // helper.setDividerHeight(1); // 设置每行Item的距离   LinearLayoutHelper布局的特有属性

            mDelegateAdapters.add(new RecommendAdapter(getContext(), helper, 1, TYPE_BANNER) {

                @Override
                public int getItemViewType(int position) {
                    return TYPE_BANNER;
                }

                @Override
                public void onBindViewHolder(RecommendViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);


                    List<String> imgList = new ArrayList<String>();

                    Banner banner = (Banner) holder.itemView.findViewById(R.id.banner);
                    banner.setImageLoader(new BannerImageLoader());

                    for (int i = 0; i < mRecommendBean.getBanners().size(); i++) {
                        imgList.add(mRecommendBean.getBanners().get(i).getThumbnail());
                    }
                    banner.setImages(imgList);

                    banner.start();

                }
            });
        }

        // icon
        if (iconCount > 0) {

            //设置复用池的大小
            RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool();
            mBinding.recyclerView.setRecycledViewPool(pool);
            pool.setMaxRecycledViews(0, 10);

            mDelegateAdapters.add(new RecommendAdapter(getContext(), new LinearLayoutHelper(), 1, TYPE_ICON) {

                @Override
                public int getItemViewType(int position) {
                    return TYPE_ICON;
                }

                @Override
                public void onBindViewHolder(RecommendViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);

                    TextView app = (TextView) holder.itemView.findViewById(R.id.tv_hot_app);
                    TextView game = (TextView) holder.itemView.findViewById(R.id.tv_hot_game);
                    TextView theme = (TextView) holder.itemView.findViewById(R.id.tv_hot_theme);
                    app.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(v.getContext(), "app", Toast.LENGTH_SHORT).show();
                        }
                    });
                    game.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(v.getContext(), "game", Toast.LENGTH_SHORT).show();
                        }
                    });
                    theme.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(v.getContext(), "theme", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            });
        }

        //热门应用推荐
        if (mAppList.size() > 0) {
            StickyLayoutHelper helper = new StickyLayoutHelper();

            mDelegateAdapters.add(new RecommendAdapter(getContext(), helper, 1, TYPE_APP_TOP) {

                @Override
                public int getItemViewType(int position) {
                    return TYPE_APP_TOP;
                }

                @Override
                public void onBindViewHolder(RecommendViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);

                    holder.itemView.setBackgroundColor(Color.parseColor("#39c6c1"));
                }
            });
        }

        // 推荐应用
        if (mAppList.size() > 0) {

            //设置复用池的大小
            RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool();
            mBinding.recyclerView.setRecycledViewPool(pool);
            pool.setMaxRecycledViews(0, 10);

            LinearLayoutHelper layoutHelper2 = new LinearLayoutHelper();
            layoutHelper2.setAspectRatio(4.0f);
            layoutHelper2.setDividerHeight(10);
            layoutHelper2.setMargin(10, 30, 10, 10);
            layoutHelper2.setPadding(10, 30, 10, 10);
            //layoutHelper2.setBgColor(0xFFF5A623);

            mDelegateAdapters.add(new RecommendAdapter(getContext(), layoutHelper2, mAppList.size(), TYPE_APP_LIST) {
                @Override
                public void onBindViewHolder(RecommendViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);

                    final AppBean appBean = mAppList.get(position);
                    Button dl = (Button) holder.itemView.findViewById(R.id.btn_dl);
                    TextView title = (TextView) holder.itemView.findViewById(R.id.text_title);
                    TextView size = (TextView) holder.itemView.findViewById(R.id.text_size);
                    ImageView img = (ImageView) holder.itemView.findViewById(R.id.img_icon);

                    title.setText(appBean.displayName);
                    size.setText(appBean.apkSize / 1024 / 1024 + " M");
                    Glide.with(getActivity()).load(baseImgUrl + appBean.icon).into(img);

                    dl.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(v.getContext(), appBean.displayName, Toast.LENGTH_SHORT).show();
                        }
                    });

                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            View itemView = holder.itemView;
                            BaseApplication.getInstance().setView(itemView);
                            AppDetailActivity.startActivity(getContext(),appBean);
                        }
                    });

                }
            });

        }

        //热门游戏推荐
        if (mGameList.size() > 0) {

            StickyLayoutHelper helper = new StickyLayoutHelper();

            mDelegateAdapters.add(new RecommendAdapter(getContext(), helper, 1, TYPE_GAME_TOP) {

                @Override
                public int getItemViewType(int position) {
                    return TYPE_GAME_TOP;
                }

                @Override
                public void onBindViewHolder(RecommendViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);

                    holder.itemView.setBackgroundColor(Color.parseColor("#39c6c1"));
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
            // helper.setBgColor(0xFFF5A623);

            mDelegateAdapters.add(new RecommendAdapter(getContext(), helper, mGameList.size(), TYPE_GAME_LIST) {
                @Override
                public void onBindViewHolder(RecommendViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);

                    final AppBean appBean = mGameList.get(position);
                    Button download = (Button) holder.itemView.findViewById(R.id.btn_dl);


                    TextView title = (TextView) holder.itemView.findViewById(R.id.text_title);
                    TextView size = (TextView) holder.itemView.findViewById(R.id.text_size);
                    ImageView img = (ImageView) holder.itemView.findViewById(R.id.img_icon);

                    title.setText(appBean.displayName);
                    size.setText(appBean.apkSize / 1024 / 1024 + " M");
                    Glide.with(getActivity()).load(baseImgUrl + appBean.icon).into(img);

                    download.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(v.getContext(), appBean.displayName, Toast.LENGTH_SHORT).show();
                        }
                    });

                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            View itemView = holder.itemView;
                            BaseApplication.getInstance().setView(itemView);
                            AppDetailActivity.startActivity(getContext(),appBean);
                        }
                    });

                }
            });
        }

        mDelegateAdapter.setAdapters(mDelegateAdapters);

        setListenerToRootView();

    }

    public void setListenerToRootView() {

    }

    public void LoadData() {
        mPresenter.getRecommendData();
    }

    @Override
    public void showResust(RecommendBean bean) {

        mRecommendBean = bean;

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

        mBinding.recyclerView.getAdapter().notifyDataSetChanged();
        System.out.println("RecommendFragment======app:" + bean.getRecommendApps().size());
        System.out.println("RecommendFragment======game:" + bean.getRecommendGames().size());
    }

    @Override
    public void showNoData() {

    }

}
