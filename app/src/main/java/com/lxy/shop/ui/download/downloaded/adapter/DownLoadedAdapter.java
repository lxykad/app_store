package com.lxy.shop.ui.download.downloaded.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lxy.shop.R;

import java.util.ArrayList;
import java.util.List;

import zlc.season.rxdownload2.entity.DownloadFlag;
import zlc.season.rxdownload2.entity.DownloadRecord;

/**
 * Created by lxy on 2017/7/5.
 */

public class DownLoadedAdapter extends BaseQuickAdapter<DownloadRecord, BaseViewHolder> {

    private List<Integer> mCheckPositionlist;

    public DownLoadedAdapter(@LayoutRes int layoutResId, @Nullable List<DownloadRecord> data) {
        super(layoutResId, data);

        mCheckPositionlist = new ArrayList<>();
    }

    @Override
    protected void convert(BaseViewHolder holder, DownloadRecord bean) {

        int position = holder.getAdapterPosition();

        holder.setText(R.id.tv, bean.getId() + "");

        CheckBox checkBox = (CheckBox) holder.itemView.findViewById(R.id.cb);

        holder.setOnCheckedChangeListener(R.id.cb, new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //Toast.makeText(buttonView.getContext(), "选中: " + position, Toast.LENGTH_SHORT).show();
                    if (!mCheckPositionlist.contains(new Integer((Integer) checkBox.getTag()))) {
                        mCheckPositionlist.add(new Integer((Integer) checkBox.getTag()));
                    }
                } else {
                   // Toast.makeText(buttonView.getContext(), "未选中: " + position, Toast.LENGTH_SHORT).show();
                    if (mCheckPositionlist.contains(new Integer((Integer) checkBox.getTag()))) {
                        mCheckPositionlist.remove(new Integer((Integer) checkBox.getTag()));
                    }
                }
            }
        });

        holder.setTag(R.id.cb, new Integer(position));

        holder.setChecked(R.id.cb, mCheckPositionlist.contains(new Integer(position)) ? true : false);

    }
}
