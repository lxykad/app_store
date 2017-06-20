package com.lxy.shop.ui;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.lxy.shop.R;
import com.lxy.shop.common.base.BaseActivity;
import com.lxy.shop.databinding.ActivityMainBinding;
import com.lxy.shop.di.component.AppComponent;
import com.lxy.shop.ui.classify.ClassifyFragment;
import com.lxy.shop.ui.game.GameFragment;
import com.lxy.shop.ui.ranking.RankingFragment;
import com.lxy.shop.ui.recommend.fragment.RecommendFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private final String[] mTitles = {"推荐", "排行", "游戏", "分类"};
    private ActivityMainBinding mBinding;
    private ArrayList<Fragment> mFragments;


    @Override
    protected void onCreate() {
        mBinding = (ActivityMainBinding) mChildBinding;

        initData();
        initEvents();

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

    }

    @Override
    protected void setActivityComponent(AppComponent appComponent) {

    }


    public void initEvents() {
        mBinding.navigationView.getHeaderView(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("header");
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
                }


                return false;
            }
        });
    }


}
