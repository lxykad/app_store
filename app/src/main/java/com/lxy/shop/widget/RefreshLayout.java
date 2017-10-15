package com.lxy.shop.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.Log;

import com.lxy.shop.R;


/**
 * Created by lxy on 2017/10/15.
 */

public class RefreshLayout extends SwipeRefreshLayout implements SwipeRefreshLayout.OnRefreshListener {

    public RefreshLayout(Context context) {
        this(context, null);
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setColorSchemeResources(R.color.colorPrimary, R.color.color_39c6c1, R.color.colorAccent);
        setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        if (mListener ==null) {
            return;
        }
        mListener.refreshData();
    }

    public void dismissRefreshing() {
        if (isRefreshing()) {
            setRefreshing(false);
        }
    }

    public void showRefreshing() {
        if (!isRefreshing()) {
            setRefreshing(true);
        }
    }


    public RefreshListener mListener;
    public void setRefreshListener(RefreshListener listener){
        mListener = listener;
    }

    public interface RefreshListener{

        void refreshData();
    }

}


