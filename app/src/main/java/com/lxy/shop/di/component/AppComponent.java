package com.lxy.shop.di.component;

import com.lxy.shop.common.base.BaseApplication;
import com.lxy.shop.common.rx.ErrorHandler;
import com.lxy.shop.data.api.ApiService;
import com.lxy.shop.di.module.AppModule;
import com.lxy.shop.di.module.DownloadModule;
import com.lxy.shop.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;
import zlc.season.rxdownload2.RxDownload;

/**
 * Created by lxy on 2017/5/11.
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class, DownloadModule.class})
public interface AppComponent {

    BaseApplication getBaseApplication();
    ApiService getApiService();
    ErrorHandler getErrorHander();

    RxDownload getRxDownload();
}
