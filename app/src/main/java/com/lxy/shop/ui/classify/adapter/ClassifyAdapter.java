package com.lxy.shop.ui.classify.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lxy.shop.R;
import com.lxy.shop.common.constant.Constant;
import com.lxy.shop.ui.classify.bean.ClassifyBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxy on 2017/6/22.
 */

public class ClassifyAdapter extends BaseQuickAdapter<ClassifyBean, BaseViewHolder> {

    private List<ClassifyBean> mList = new ArrayList<>();

    public ClassifyAdapter(@LayoutRes int layoutResId, @Nullable List<ClassifyBean> data) {
        super(layoutResId, data);
        mList = data;
    }

    @Override
    protected void convert(BaseViewHolder holder, ClassifyBean bean) {

        //ClassifyBean classifyBean = mList.get(holder.getAdapterPosition());

        holder.setText(R.id.text_name, bean.getName());
        ImageView img = (ImageView) holder.itemView.findViewById(R.id.img_icon);

        Glide.with(mContext).load(Constant.BASE_IMG_URL + bean.getIcon()).into(img);

    }
}
