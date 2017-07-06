package com.lxy.shop.ui.download.adapter;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lxy.shop.R;
import com.lxy.shop.common.constant.Constant;
import com.lxy.shop.ui.recommend.AppBean;
import com.lxy.shop.widget.DownloadButtonConntroller;
import com.lxy.shop.widget.DownloadProgressButton;

import java.util.List;

import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadRecord;

/**
 * Created by lxy on 2017/7/5.
 */

public class DownloadAdapter extends BaseQuickAdapter<DownloadRecord, BaseViewHolder> {

    private DownloadButtonConntroller mDownloadButtonConntroller;

    public DownloadAdapter(@LayoutRes int layoutResId, @Nullable List<DownloadRecord> data, RxDownload rxDownload, Activity activity) {
        super(layoutResId, data);

        mDownloadButtonConntroller = new DownloadButtonConntroller(rxDownload, activity);

        openLoadAnimation();
    }

    @Override
    protected void convert(BaseViewHolder holder, DownloadRecord item) {

        AppBean appInfo = mDownloadButtonConntroller.downloadRecord2AppBean(item);

        holder.setText(R.id.text_title, appInfo.displayName)
                .setText(R.id.text_size, appInfo.apkSize / 1024 / 1024 + " M");
        ImageView img = (ImageView) holder.itemView.findViewById(R.id.img_icon);
        Glide.with(mContext).load(Constant.BASE_IMG_URL + appInfo.icon).into(img);

        DownloadProgressButton button = (DownloadProgressButton) holder.itemView.findViewById(R.id.btn_dl);

        mDownloadButtonConntroller.handClick(button, item);
    }
}
