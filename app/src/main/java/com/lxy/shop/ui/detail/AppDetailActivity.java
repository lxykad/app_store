package com.lxy.shop.ui.detail;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaCodecInfo;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.lxy.shop.R;
import com.lxy.shop.common.base.BaseActivity;
import com.lxy.shop.common.base.BaseApplication;
import com.lxy.shop.databinding.ActivityAppDetailBinding;
import com.lxy.shop.di.component.AppComponent;
import com.lxy.shop.ui.detail.presenter.AppDetailPresenter;
import com.lxy.shop.ui.recommend.AppBean;
import com.lxy.shop.util.Tool;

/**
 * Created by lxy on 2017/6/22.
 */

public class AppDetailActivity extends BaseActivity<AppDetailPresenter> {


    public static final String APP_BEAN = "app_bean";
    private View mItemView;
    private AppBean mAppBean;
    private ActivityAppDetailBinding mBinding;
    private int mItemHeight;

    public static void startActivity(Context context, AppBean bean) {

        Intent intent = new Intent(context, AppDetailActivity.class);
        intent.putExtra(APP_BEAN, bean);
        context.startActivity(intent);
    }

    @Override
    protected void setActivityComponent(AppComponent appComponent) {

    }

    @Override
    protected void onCreate() {

        mBinding = (ActivityAppDetailBinding) mChildBinding;

        mAppBean = (AppBean) getIntent().getSerializableExtra(APP_BEAN);
        mItemView = BaseApplication.getInstance().getView();

       // mBinding.TemplateTitle.setTitleText(mAppBean.displayName);

        initToolBar();
        initItem();
        initAnimation();

    }

    @Override
    protected int setLayoutId() {

        return R.layout.activity_app_detail;

    }

    public void initToolBar(){
        setSupportActionBar(mBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void initItem() {
        View view = BaseApplication.getInstance().getView();
        mItemHeight = view.getHeight();
        Bitmap bitmap = getCacheImage(view);

        if (bitmap != null) {
            mBinding.viewItem.setBackgroundDrawable(new BitmapDrawable(bitmap));
        }

        int[] location = new int[2];
        view.getLocationOnScreen(location);

        int left = location[0];
        int top = location[1];

        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(mBinding.viewItem.getLayoutParams());
        params.topMargin = top - Tool.getStatusBarHeight(this);
        params.leftMargin = left;
        params.width = view.getWidth();
        params.height = view.getHeight();

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(params);

        mBinding.viewItem.setLayoutParams(layoutParams);

    }

    public Bitmap getCacheImage(View view) {

        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();

        Bitmap bitmap = view.getDrawingCache();
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight());
        view.destroyDrawingCache();

        return bitmap;
    }

    public void initAnimation() {

        int screenHeight = Tool.getScreenHeight(this);
        int count = screenHeight / mItemHeight;

        ObjectAnimator animator = ObjectAnimator.ofFloat(mBinding.viewItem, "scaleY", 1f, (float) count);
        animator.setStartDelay(500);
        animator.setDuration(300);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                mBinding.viewItem.setVisibility(View.GONE);
                initContent();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);

                mBinding.viewItem.setBackgroundColor(getResources().getColor(R.color.white));
            }
        });

        animator.start();

    }

    public void initContent() {
        mBinding.contentLayout.setVisibility(View.VISIBLE);
    }
}
