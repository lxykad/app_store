package com.lxy.shop.ui.download.constract;

import com.lxy.shop.common.base.BaseView;
import com.lxy.shop.ui.recommend.AppBean;

import java.util.List;

import io.reactivex.Observable;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadRecord;

/**
 * Created by lxy on 2017/7/5.
 */

public interface DownloadConstract {

    interface AppManagerView extends BaseView {

        void showDownloading(List<DownloadRecord> downloadRecords);

        //  void showApps(List<AndroidApk> apps);

        void showUpdateApps(List<AppBean> appInfos);
    }

    interface IAppManagerModel {

        Observable<List<DownloadRecord>> getDownloadRecord();

        RxDownload getRxDownload();

        // Observable<List<AndroidApk>> getLocalApks();

        // Observable<List<AndroidApk>> getInstalledApps();

    }

}
