package com.lxy.shop.ui.recommend.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lxy.shop.R;
import com.lxy.shop.ui.recommend.AppBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxy on 2017/6/9.
 */

public class RecommendAdapter extends DelegateAdapter.Adapter<RecommendAdapter.RecommendViewHolder> {

    private List<AppBean> mList = new ArrayList<>();
    private Context mContext;
    String baseImgUrl = "http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";


    private LayoutHelper mLayoutHelper;
    private RecyclerView.LayoutParams mLayoutParams;
    private int mCount;

    public RecommendAdapter(Context context, LayoutHelper helper, int count) {

        this(context, helper, count, new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

    }

    public RecommendAdapter(Context context, LayoutHelper helper, int count, RecyclerView.LayoutParams params) {

        mContext = context;
        mLayoutParams = params;
        mCount = count;
        mLayoutHelper = helper;

    }

    public void addItems(List<AppBean> list) {
        mList.clear();
        mList.addAll(list);
        // notifyDataSetChanged();
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {

        return mLayoutHelper;
    }

    @Override
    public RecommendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_recommend_fragment, parent, false);
        return new RecommendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecommendViewHolder holder, int position) {

       // holder.title.setText("" + position);
    }

    @Override
    public int getItemCount() {
        return mCount;
    }


    public static class RecommendViewHolder extends RecyclerView.ViewHolder {

        public TextView title;

        public RecommendViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.text_title);
        }
    }

}
