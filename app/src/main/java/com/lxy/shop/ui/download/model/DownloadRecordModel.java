package com.lxy.shop.ui.download.model;

import android.content.Context;

import java.util.List;

import io.reactivex.Observable;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadRecord;

/**
 * Created by lxy on 2017/7/5.
 */

public class DownloadRecordModel{

    private RxDownload mRxDownload;

    public DownloadRecordModel(RxDownload mRxDownload) {
        this.mRxDownload = mRxDownload;
    }

    public Observable<List<DownloadRecord>> getDownloadRecord(){

        return mRxDownload.getTotalDownloadRecords();
    }
}
