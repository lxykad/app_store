package com.lxy.shop.di.module;

import com.lxy.shop.ui.download.constract.DownloadConstract;
import com.lxy.shop.ui.download.model.DownloadRecordModel;

import dagger.Module;
import dagger.Provides;
import zlc.season.rxdownload2.RxDownload;

/**
 * Created by lxy on 2017/7/5.
 */

@Module
public class DownloadRecordMoudle {

    public DownloadConstract.AppManagerView mView;

    public DownloadRecordMoudle(DownloadConstract.AppManagerView view) {

        mView = view;
    }

    @Provides
    public DownloadConstract.AppManagerView provideView() {

        return mView;
    }

    @Provides
    public DownloadRecordModel providesModel(RxDownload rxDownload){

        return new DownloadRecordModel(rxDownload);
    }
}
