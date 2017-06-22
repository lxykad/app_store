package com.lxy.shop.ui.recommend.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.lxy.shop.R;

/**
 * Created by lxy on 2017/6/9.
 */

public class RecommendAdapter extends DelegateAdapter.Adapter<RecommendAdapter.RecommendViewHolder> {

    private static final int TYPE_BANNER = 1;
    private static final int TYPE_ICON = 2;
    private static final int TYPE_APP_TOP = 3;
    private static final int TYPE_GAME_TOP = 4;
    private static final int TYPE_APP_LIST = 5;
    private static final int TYPE_GAME_LIST = 6;

    private Context mContext;

    private LayoutHelper mLayoutHelper;
    private RecyclerView.LayoutParams mLayoutParams;
    private int mCount;
    private int mItemType;

    public RecommendAdapter(Context context, LayoutHelper helper, int count, int type) {

        this(context, helper, count, type, new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

    }

    public RecommendAdapter(Context context, LayoutHelper helper, int count, int type, RecyclerView.LayoutParams params) {

        mContext = context;
        mLayoutParams = params;
        mCount = count;
        mLayoutHelper = helper;
        mItemType = type;

    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {

        return mLayoutHelper;
    }

    @Override
    public RecommendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;

        switch (mItemType) {

            case TYPE_BANNER:
                view = LayoutInflater.from(mContext).inflate(R.layout.list_item_recommend_banner, parent, false);
                break;
            case TYPE_ICON:
                view = LayoutInflater.from(mContext).inflate(R.layout.list_item_recommend_icon, parent, false);
                break;
            case TYPE_APP_TOP:
                view = LayoutInflater.from(mContext).inflate(R.layout.list_item_recommend_apps_top, parent, false);
                break;
            case TYPE_APP_LIST:
                view = LayoutInflater.from(mContext).inflate(R.layout.list_item_recommend_fragment, parent, false);
                break;
            case TYPE_GAME_TOP:
                view = LayoutInflater.from(mContext).inflate(R.layout.list_item_recommend_games_top, parent, false);
                break;
            case TYPE_GAME_LIST:
                view = LayoutInflater.from(mContext).inflate(R.layout.list_item_recommend_fragment, parent, false);
                break;
        }

        return new RecommendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecommendViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mCount;
    }

    public static class RecommendViewHolder extends RecyclerView.ViewHolder {


        public RecommendViewHolder(View itemView) {
            super(itemView);

        }

    }

}
