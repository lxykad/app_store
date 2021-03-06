package com.lxy.shop.ui.recommend;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.lxy.shop.ui.download.bean.AppDownloadInfo;

import java.io.Serializable;

import zlc.season.rxdownload2.entity.DownloadEvent;

/**
 * Created by lxy on 2017/5/11.
 */

public class AppBean implements Serializable, MultiItemEntity {

    public int adType;
    public int ads;
    public long apkSize;
    public int appendSize;
    public String briefShow;
    public boolean briefUseIntro;
    public String displayName;
    public String icon;
    public int id;
    public int level1CategoryId;
    public String level1CategoryName;
    public int level2CategoryId;
    public String packageName;
    public int position;
    public String publisherName;
    public int rId;
    public double ratingScore;
    public String releaseKeyHash;
    public String screenshot;
    public String source;
    public int suitableType;
    public long updateTime;
    public int versionCode;
    public String versionName;
    public int videoId;

    public int mItemType;

    public AppDownloadInfo mAppDownloadInfo;
    public int adapterPos;

    @Override
    public String toString() {
        return "AppBean{" +
                "adType=" + adType +
                ", ads=" + ads +
                ", apkSize=" + apkSize +
                ", appendSize=" + appendSize +
                ", briefShow='" + briefShow + '\'' +
                ", briefUseIntro=" + briefUseIntro +
                ", displayName='" + displayName + '\'' +
                ", icon='" + icon + '\'' +
                ", id=" + id +
                ", level1CategoryId=" + level1CategoryId +
                ", level1CategoryName='" + level1CategoryName + '\'' +
                ", level2CategoryId=" + level2CategoryId +
                ", packageName='" + packageName + '\'' +
                ", position=" + position +
                ", publisherName='" + publisherName + '\'' +
                ", rId=" + rId +
                ", ratingScore=" + ratingScore +
                ", releaseKeyHash='" + releaseKeyHash + '\'' +
                ", screenshot='" + screenshot + '\'' +
                ", source='" + source + '\'' +
                ", suitableType=" + suitableType +
                ", updateTime=" + updateTime +
                ", versionCode=" + versionCode +
                ", versionName='" + versionName + '\'' +
                ", videoId=" + videoId +
                '}';
    }

    public AppBean() {

    }

    public AppBean(int itemType) {
        mItemType = itemType;
    }

    @Override
    public int getItemType() {

        return mItemType;
    }

}
