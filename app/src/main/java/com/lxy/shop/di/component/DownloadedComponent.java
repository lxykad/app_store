package com.lxy.shop.di.component;

import com.lxy.shop.di.module.DownloadRecordMoudle;
import com.lxy.shop.di.scope.ActivityScope;
import com.lxy.shop.ui.download.downloading.DownLoadingFragment;

import dagger.Component;

/**
 * Created by lxy on 2017/7/5.
 */

@ActivityScope
@Component(modules = DownloadRecordMoudle.class,dependencies = AppComponent.class)
public interface DownloadedComponent {

    void inject(DownLoadingFragment fragment);
}
