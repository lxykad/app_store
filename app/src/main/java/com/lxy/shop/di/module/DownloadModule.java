package com.lxy.shop.di.module;

import android.os.Environment;

import com.lxy.shop.common.base.BaseApplication;
import com.lxy.shop.common.constant.Constant;
import com.orhanobut.hawk.Hawk;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import zlc.season.rxdownload2.RxDownload;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

@Module
public class DownloadModule {

    @Provides
    @Singleton
    public RxDownload provideRxDownload(Retrofit retrofit, File downDir) {

        Hawk.put(Constant.APK_DOWNLOAD_DIR, downDir.getPath());

        return RxDownload.getInstance(BaseApplication.getInstance())
                .defaultSavePath(downDir.getPath())
                .retrofit(retrofit)
                .maxDownloadNumber(10)
                .maxThread(10);
    }

    @Singleton
    @Provides
    public File provideDownloadDir() {

        return Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS);

    }

}
