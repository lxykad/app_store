package com.lxy.shop.ui.ranking.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lxy.shop.R;
import com.lxy.shop.ui.recommend.AppBean;
import com.lxy.shop.widget.DownloadButtonConntroller;
import com.lxy.shop.widget.DownloadProgressButton;

import java.util.ArrayList;
import java.util.List;

import zlc.season.rxdownload2.RxDownload;

/**
 * Created by lxy on 2017/6/16.
 */

public class RankAdapter extends BaseQuickAdapter<AppBean, BaseViewHolder> {

    private String baseImgUrl = "http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";
    private List<AppBean> mList = new ArrayList<>();

    private DownloadButtonConntroller mDownloadButtonConntroller;

    public RankAdapter(@LayoutRes int layoutResId, @Nullable List<AppBean> data, Context context) {
        super(layoutResId, data);

        mDownloadButtonConntroller = new DownloadButtonConntroller(RxDownload.getInstance(context));
        mList = data;
    }

    @Override
    protected void convert(BaseViewHolder holder, AppBean item) {

        int position = holder.getAdapterPosition();
        AppBean bean = mList.get(position);

        holder.setText(R.id.text_title, bean.displayName)
                .setText(R.id.tv_index, position + 1 + ". ")
                .setText(R.id.text_size, bean.apkSize / 1024 / 1024 + " M")
              //  .addOnClickListener(R.id.btn_dl)
                ;

        ImageView img = (ImageView) holder.itemView.findViewById(R.id.img_icon);
        Glide.with(mContext).load(baseImgUrl + bean.icon).into(img);

        DownloadProgressButton button = (DownloadProgressButton) holder.itemView.findViewById(R.id.btn_dl);
        mDownloadButtonConntroller.handClick(button,item);
    }
}
