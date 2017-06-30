package com.lxy.shop.widget.behavior;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;

/**
 * Created by lxy on 2017/6/30.
 */

public class AppBarLayoutBehavior extends AppBarLayout.Behavior {

    private static final String TAG = "overScroll";
    private View mTargetView;       // 目标View
    private int mParentHeight;      // AppBarLayout的初始高度
    private int mTargetViewHeight;  // 目标View的高度

    private boolean isAnimate;  //是否有动画  快速滑动处理
    private boolean isRecovering = false;//是否正在自动回弹中

    public AppBarLayoutBehavior() {
    }

    public AppBarLayoutBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, AppBarLayout abl, int layoutDirection) {

        boolean superBoolean = super.onLayoutChild(parent, abl, layoutDirection);

        // 调用过super.onLayoutChild()方法之后获取 目标View
        if (mTargetView == null) {
            mTargetView = parent.findViewWithTag(TAG);
            if (mTargetView != null) {
                initial(abl);
            }
        }

        return superBoolean;
    }

    private static final float TARGET_HEIGHT = 500; // 最大滑动距离
    private float mTotalDy;     // 总滑动的像素数
    private float mLastScale;   // 最终放大比例
    private int mLastBottom;    // AppBarLayout的最终Bottom值

    /**
     * 上下滑动处理
     * AppBarLayout上滑时不会调用onNestedScroll() ,所以在此方法中处理上滑和下滑
     *
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param dx
     * @param dy
     * @param consumed
     */

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);

        // 1.mTargetView不为null
        // 2.是向下滑动，dy<0表示向下滑动
        // 3.AppBarLayout已经完全展开，child.getBottom() >= mParentHeight

        if (mTargetView != null && dy < 0 && child.getBottom() >= mParentHeight) {// 向下滑动
            // 累加垂直方向上滑动的像素数
            mTotalDy += -dy;
            // 不能大于最大滑动距离
            mTotalDy = Math.min(mTotalDy, TARGET_HEIGHT);
            // 计算目标View缩放比例，不能小于1
            mLastScale = Math.max(1f, 1f + mTotalDy / TARGET_HEIGHT);
            // 缩放目标View
            ViewCompat.setScaleX(mTargetView, mLastScale);
            ViewCompat.setScaleY(mTargetView, mLastScale);
            // 计算目标View放大后增加的高度
            mLastBottom = mParentHeight + (int) (mTargetViewHeight / 2 * (mLastScale - 1));
            // 修改AppBarLayout的高度
            child.setBottom(mLastBottom);

            LogUtils.d("scroll==========down");

        } else if (mTargetView != null && dy > 0 && child.getBottom() > mParentHeight) {//向上滑动

            // 累减垂直方向上滑动的像素数
            mTotalDy -= dy;
            // 计算目标View缩放比例，不能小于1
            mLastScale = Math.max(1f, 1f + mTotalDy / TARGET_HEIGHT);
            // 缩放目标View
            ViewCompat.setScaleX(mTargetView, mLastScale);
            ViewCompat.setScaleY(mTargetView, mLastScale);
            // 计算目标View缩小后减少的高度
            mLastBottom = mParentHeight + (int) (mTargetViewHeight / 2 * (mLastScale - 1));
            // 修改AppBarLayout的高度
            child.setBottom(mLastBottom);
            // 保持target不滑动
            target.setScrollY(0);

            LogUtils.d("scroll==========up");

        } else {
            super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        }


//

//        if (!isRecovering) {
//
//            if (mTargetView != null && ((dy < 0 && child.getBottom() >= mParentHeight) || (dy > 0 && child.getBottom() > mParentHeight))) {
//                scale(child, target, dy);
//                return;
//            }
//        }
//        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
    }

    //还原
    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout abl, View target) {
        recovery(abl);
        super.onStopNestedScroll(coordinatorLayout, abl, target);
    }

    // 快速滑动处理
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout parent, AppBarLayout child, View directTargetChild, View target, int nestedScrollAxes) {
        isAnimate = true;

        return super.onStartNestedScroll(parent, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, float velocityX, float velocityY, boolean consumed) {

        // 如果触发了快速滚动且垂直方向上速度大于100，则禁用动画
        if (velocityY > 100) {
            isAnimate = false;
        }

        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }

    private void initial(AppBarLayout appBarLayout) {

        // 必须设置ClipChildren为false，这样目标View在放大时才能超出布局的范围
        appBarLayout.setClipChildren(false);
        mParentHeight = appBarLayout.getHeight();
        mTargetViewHeight = mTargetView.getHeight();
    }

    public void scale(AppBarLayout appBarLayout, View targetView, int dy) {
        mTotalDy += -dy;
        mTotalDy = Math.min(mTotalDy, TARGET_HEIGHT);
        mLastScale = Math.max(1f, 1f + mTotalDy / TARGET_HEIGHT);
        ViewCompat.setScaleX(mTargetView, mLastScale);
        ViewCompat.setScaleY(mTargetView, mLastScale);
        mLastBottom = mParentHeight + (int) (mTargetViewHeight / 2 * (mLastScale - 1));
        appBarLayout.setBottom(mLastBottom);
        targetView.setScrollY(0);
    }

    private void recovery(final AppBarLayout abl) {

        if (isRecovering) return;

        if (mTotalDy > 0) {
            isRecovering = true;
            mTotalDy = 0;
            if (isAnimate) {
                ValueAnimator anim = ValueAnimator.ofFloat(mLastScale, 1f).setDuration(200);
                anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float value = (float) animation.getAnimatedValue();
                        ViewCompat.setScaleX(mTargetView, value);
                        ViewCompat.setScaleY(mTargetView, value);
                        abl.setBottom((int) (mLastBottom - (mLastBottom - mParentHeight) * animation.getAnimatedFraction()));
                    }
                });
                anim.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        isRecovering = false;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                });
                anim.start();
            } else {

                ViewCompat.setScaleX(mTargetView, 1f);
                ViewCompat.setScaleY(mTargetView, 1f);
                abl.setBottom(mParentHeight);
                isRecovering = false;

            }
        }
    }

}
