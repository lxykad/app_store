package com.lxy.shop.ui.download.Presenter;

import com.lxy.shop.common.base.BasePresenter;
import com.lxy.shop.common.rx.RxSchedulers;
import com.lxy.shop.common.rx.observer.ProgressObserver;
import com.lxy.shop.ui.download.constract.DownloadConstract;
import com.lxy.shop.ui.download.model.DownloadRecordModel;

import java.util.List;

import javax.inject.Inject;

import zlc.season.rxdownload2.entity.DownloadRecord;

/**
 * Created by lxy on 2017/7/5.
 */

public class DownloadRecordPresenter extends BasePresenter<DownloadRecordModel, DownloadConstract.AppManagerView> {

    @Inject
    public DownloadRecordPresenter(DownloadRecordModel mModel, DownloadConstract.AppManagerView mView) {
        super(mModel, mView);
    }

    public void getRecords() {
        mModel.getDownloadRecord()
                .compose(RxSchedulers.io_main())
                .subscribe(new ProgressObserver<List<DownloadRecord>>(mContext, mView) {
                    @Override
                    public void onNext(List<DownloadRecord> downloadRecords) {
                        mView.showDownloading(downloadRecords);
                    }
                });
    }

}
