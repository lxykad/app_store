package com.lxy.shop.ui.recommend.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lxy.shop.R;
import com.lxy.shop.ui.recommend.AppBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxy on 2017/6/9.
 */

public class RecommendAdapter extends BaseQuickAdapter<AppBean, BaseViewHolder> {

    private List<AppBean> mList = new ArrayList<>();

    public void addItems(List<AppBean> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public RecommendAdapter(@LayoutRes int layoutResId, @Nullable List<AppBean> list) {
        super(layoutResId, list);

        mList = list;
    }

    @Override
    protected void convert(BaseViewHolder holder, AppBean bean) {

        holder.setText(R.id.text_title, bean.displayName)
                .setText(R.id.text_size, bean.apkSize / 1024 / 1024 + " M");

        holder.addOnClickListener(R.id.btn_dl);

    }
}
