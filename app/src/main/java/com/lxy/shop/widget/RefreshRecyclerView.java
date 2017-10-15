package com.lxy.shop.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by lxy on 2017/10/14.
 */

public class RefreshRecyclerView extends RecyclerView implements SwipeRefreshLayout.OnRefreshListener {

    public RefreshRecyclerView(Context context) {
        this(context, null);
    }

    public RefreshRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {

        SwipeRefreshLayout layout = new SwipeRefreshLayout(context);
       // layout.addView(RefreshRecyclerView.this);


        layout.setOnRefreshListener(this);
        System.out.println("=============child=" + layout.getChildCount());
    }

    @Override
    public void onRefresh() {
        System.out.println("=============onRefresh");
    }
}
