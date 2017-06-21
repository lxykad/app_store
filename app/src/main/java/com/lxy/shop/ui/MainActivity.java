package com.lxy.shop.ui;

import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.CacheUtils;
import com.bumptech.glide.Glide;
import com.lxy.shop.R;
import com.lxy.shop.common.base.BaseActivity;
import com.lxy.shop.common.constant.Constant;
import com.lxy.shop.common.user.User;
import com.lxy.shop.databinding.ActivityMainBinding;
import com.lxy.shop.di.component.AppComponent;
import com.lxy.shop.ui.classify.ClassifyFragment;
import com.lxy.shop.ui.game.GameFragment;
import com.lxy.shop.ui.login.LoginActivity;
import com.lxy.shop.ui.login.event.LogoutEvent;
import com.lxy.shop.ui.ranking.RankingFragment;
import com.lxy.shop.ui.recommend.fragment.RecommendFragment;
import com.orhanobut.hawk.Hawk;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class MainActivity extends BaseActivity {

    private final String[] mTitles = {"推荐", "排行", "游戏", "分类"};
    private ActivityMainBinding mBinding;
    private ArrayList<Fragment> mFragments;


    @Override
    protected void onCreate() {

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        mBinding = (ActivityMainBinding) mChildBinding;

        initData();
        initEvents();

        initUser();

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    private void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(new RecommendFragment());
        mFragments.add(new RankingFragment());
        mFragments.add(new GameFragment());
        mFragments.add(new ClassifyFragment());

        mBinding.slidingTabLayout.setViewPager(mBinding.viewPager, mTitles, this, mFragments);

        mBinding.titleLayout.setMoreImg(R.drawable.ic_search_white_24dp);
        mBinding.titleLayout.setTitleText("应用商店");
        mBinding.titleLayout.setBackImg(R.drawable.ic_menu_white_24dp);
    }

    @Override
    protected void setActivityComponent(AppComponent appComponent) {

    }

    public void initUser() {

        User user = Hawk.get(Constant.USER, null);

        if (user != null) {

            setUserInfo(user);

            mBinding.navigationView.getHeaderView(0).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    showToast("已登录");
                }
            });

        } else {
            setUserInfo(null);

            mBinding.navigationView.getHeaderView(0).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            });
        }

    }

    public void initEvents() {

        mBinding.titleLayout.setMoreImgAction(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("search");
            }
        });

        mBinding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.menu_app_update:

                        showToast("update");
                        break;
                    case R.id.menu_message:

                        showToast("message");
                        break;
                    case R.id.menu_setting:

                        showToast("setting");
                        break;

                    case R.id.menu_logout:


                        User user = Hawk.get(Constant.USER, null);
                        if (user == null) {
                            showToast("未登录");
                        } else {
                            showToast("退出登录成功");
                        }

                        Hawk.put(Constant.USER, null);
                        Hawk.put(Constant.TOKEN, "");
                        EventBus.getDefault().post(new LogoutEvent());
                        break;
                }

                return false;
            }
        });
    }

    public void setUserInfo(User user) {

        TextView name = (TextView) mBinding.navigationView.getHeaderView(0).findViewById(R.id.tv_name);
        ImageView avatar = (ImageView) mBinding.navigationView.getHeaderView(0).findViewById(R.id.iv_avatar);

        if (user != null) {
            name.setText(user.username);
            Glide.with(this).load(user.logo_url).bitmapTransform(new CropCircleTransformation(this)).into(avatar);
        } else {
            name.setText("请登录");
            Glide.with(this).load(R.mipmap.ic_launcher).bitmapTransform(new CropCircleTransformation(this)).into(avatar);
        }
    }

    @Subscribe
    public void onLoginSuccess(User user) {
        initUser();
    }

    @Subscribe
    public void onLogoutSuccess(LogoutEvent event) {
        initUser();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
