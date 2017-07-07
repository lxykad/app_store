package com.lxy.shop.widget;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import com.jakewharton.rxbinding2.view.RxView;
import com.lxy.shop.R;
import com.lxy.shop.common.base.BaseApplication;
import com.lxy.shop.common.constant.Constant;
import com.lxy.shop.common.http.ParamsInterceptor;
import com.lxy.shop.common.rx.BaseBean;
import com.lxy.shop.common.rx.RxHttpResponse;
import com.lxy.shop.common.rx.RxSchedulers;
import com.lxy.shop.data.api.ApiService;
import com.lxy.shop.ui.download.bean.AppDownloadInfo;
import com.lxy.shop.ui.recommend.AppBean;
import com.lxy.shop.util.AppUtils;
import com.lxy.shop.util.PermissionUtil;
import com.orhanobut.hawk.Hawk;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadBean;
import zlc.season.rxdownload2.entity.DownloadEvent;
import zlc.season.rxdownload2.entity.DownloadFlag;
import zlc.season.rxdownload2.entity.DownloadRecord;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class DownloadButtonConntroller {

    private RxDownload mRxDownload;

    private DownLoadAppApi mApi;

    private Activity mActivity;

    public DownloadButtonConntroller(RxDownload downloader, Activity activity) {

        this.mRxDownload = downloader;
        mActivity = activity;
        if (mRxDownload != null) {

            Gson gson = new Gson();

            OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
            okHttpBuilder
                    .addInterceptor(new ParamsInterceptor(BaseApplication.getInstance(), gson))
                    // 连接超时时间设置
                    .connectTimeout(10, TimeUnit.SECONDS)
                    // 读取超时时间设置
                    .readTimeout(10, TimeUnit.SECONDS);

            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(ApiService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpBuilder.build());

            mApi = builder.build().create(DownLoadAppApi.class);

        }

    }

    public void handClick(final DownloadProgressButton btn, DownloadRecord record) {

        AppBean info = downloadRecord2AppBean(record);

        receiveDownloadStatus(record.getUrl()).subscribe(new DownloadConsumer(btn, info));

    }

    public void handClick(final DownloadProgressButton btn, final AppBean AppBean) {

        if (mApi == null) {
            return;
        }

        isAppInstalled(btn.getContext(), AppBean)

//                .flatMap(new Function<DownloadEvent, ObservableSource<DownloadEvent>>() {
//                    @Override
//                    public ObservableSource<DownloadEvent> apply(@NonNull DownloadEvent event)
//                            throws Exception {
//
//                        if (DownloadFlag.NORMAL == event.getFlag()) {//未安装
//
//                            return isApkFileExsit(btn.getContext(), AppBean);
//
//                        }
//                        return Observable.just(event);
//
//                    }
//                })
//                .flatMap(new Function<DownloadEvent, ObservableSource<DownloadEvent>>() {
//                    @Override
//                    public ObservableSource<DownloadEvent> apply(@NonNull DownloadEvent event) throws Exception {
//
//                        if (DownloadFlag.NORMAL == event.getFlag()) {
//
//                            return getAppDownloadInfo(AppBean)
//                                    .flatMap(new Function<AppDownloadInfo, ObservableSource<DownloadEvent>>() {
//                                        @Override
//                                        public ObservableSource<DownloadEvent> apply(@NonNull AppDownloadInfo appDownloadInfo) throws Exception {
//
//                                            AppBean.mAppDownloadInfo = appDownloadInfo;
//
//                                            return receiveDownloadStatus(appDownloadInfo.getDownloadUrl());
//                                        }
//                                    });
//
//                        }
//
//                        return Observable.just(event);
//                    }
//                })
                .toObservable()
                .compose(RxSchedulers.<DownloadEvent>io_main())
                .subscribe(new DownloadConsumer(btn, AppBean));

    }

    //按钮的点击事件
    private void bindClick(final DownloadProgressButton btn, final AppBean AppBean) {
        btn.setOnClickListener(v->{
            int flag = (int) btn.getTag(R.id.tag_apk_flag);

            switch (flag) {

                case DownloadFlag.INSTALLED:
                    runApp(btn.getContext(), AppBean.packageName);
                    break;

                // 升级 未处理

                case DownloadFlag.STARTED:
                    pausedDownload(AppBean.mAppDownloadInfo.getDownloadUrl());
                    break;

                case DownloadFlag.NORMAL:
                case DownloadFlag.PAUSED:
                    startDownload(btn, AppBean);

                    break;

                case DownloadFlag.COMPLETED:
                    installApp(btn.getContext(), AppBean);

                    break;

            }
        });
    }

    private void installApp(Context context, AppBean AppBean) {

        String path = Hawk.get((Constant.APK_DOWNLOAD_DIR) + File.separator + AppBean.releaseKeyHash, null);
        if (path != null) {
            //PackageUtils.install(context, path);
        }
    }

    // 动态权限申请
    private void startDownload(final DownloadProgressButton btn, final AppBean AppBean) {

        PermissionUtil.requestPermisson(mActivity, WRITE_EXTERNAL_STORAGE)

                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean aBoolean) throws Exception {

                        if (aBoolean) { // 授权

                            final AppDownloadInfo downloadInfo = AppBean.mAppDownloadInfo;

                            Toast.makeText(btn.getContext(), downloadInfo + "", Toast.LENGTH_SHORT).show();

                            if (downloadInfo == null) {

                                getAppDownloadInfo(AppBean).subscribe(new Consumer<AppDownloadInfo>() {
                                    @Override
                                    public void accept(@NonNull AppDownloadInfo appDownloadInfo) throws Exception {

                                        AppBean.mAppDownloadInfo = appDownloadInfo;
                                        download(btn, AppBean);

                                    }

                                });
                            } else {

                                download(btn, AppBean);
                            }
                        }

                    }
                });

    }

    //下载app
    private void download(DownloadProgressButton btn, AppBean info) {


        mRxDownload.serviceDownload(AppBean2DownloadBean(info)).subscribe();

        mRxDownload.receiveDownloadStatus(info.mAppDownloadInfo.getDownloadUrl()).subscribe(new DownloadConsumer(btn, info));

    }

    private DownloadBean AppBean2DownloadBean(AppBean info) {

        DownloadBean downloadBean = new DownloadBean();

        downloadBean.setUrl(info.mAppDownloadInfo.getDownloadUrl());
        downloadBean.setSaveName(info.releaseKeyHash + ".apk");


        downloadBean.setExtra1(info.id + "");
        downloadBean.setExtra2(info.icon);
        downloadBean.setExtra3(info.displayName);
        downloadBean.setExtra4(info.packageName);
        downloadBean.setExtra5(info.releaseKeyHash);

        return downloadBean;
    }

    public AppBean downloadRecord2AppBean(DownloadRecord bean) {


        AppBean info = new AppBean();

        info.id = Integer.parseInt(bean.getExtra1());
        info.icon = bean.getExtra2();
        info.displayName = bean.getExtra3();
        info.packageName = bean.getExtra4();
        info.releaseKeyHash = bean.getExtra5();


        AppDownloadInfo downloadInfo = new AppDownloadInfo();

        downloadInfo.setDowanloadUrl(bean.getUrl());

        info.mAppDownloadInfo = downloadInfo;

        return info;

    }

    private void pausedDownload(String url) {


        mRxDownload.pauseServiceDownload(url).subscribe();
    }

    private void runApp(Context context, String packageName) {

        AppUtils.runApp(context, packageName);
    }

    //判断app 是否已经安装
    public Single<DownloadEvent> isAppInstalled(Context context, AppBean appBean) {
        return Single.create(new SingleOnSubscribe<DownloadEvent>() {
            @Override
            public void subscribe(SingleEmitter<DownloadEvent> e) throws Exception {
                DownloadEvent event = new DownloadEvent();
                // 耗时操作，待优化
                event.setFlag(AppUtils.isInstalled(context, appBean.packageName) ? DownloadFlag.INSTALLED : DownloadFlag.NORMAL);
                e.onSuccess(event);
            }
        });
    }

    // 判断app 是否已经下载（可能下载完成 也可能未完成）
    public Observable<DownloadEvent> isApkFileExsit(Context context, AppBean AppBean) {

        String path = Hawk.get((Constant.APK_DOWNLOAD_DIR) + File.separator + AppBean.releaseKeyHash);

        File file = new File(path);

        DownloadEvent event = new DownloadEvent();

        event.setFlag(file.exists() ? DownloadFlag.COMPLETED : DownloadFlag.NORMAL);

        return Observable.just(event);

    }


    public Observable<DownloadEvent> receiveDownloadStatus(String url) {

        return mRxDownload.receiveDownloadStatus(url);
    }

    public Observable<AppDownloadInfo> getAppDownloadInfo(AppBean AppBean) {

        return mApi.getAppDownloadInfo(AppBean.id).compose(RxHttpResponse.handResult());
    }

    // 适配器列表 及下载状态处理
    class DownloadConsumer implements Consumer<DownloadEvent> {

        DownloadProgressButton btn;

        AppBean mAppBean;

        public DownloadConsumer(DownloadProgressButton b, AppBean appBean) {

            this.btn = b;
            this.mAppBean = appBean;
        }

        @Override
        public void accept(@NonNull DownloadEvent event) throws Exception {
            int flag = event.getFlag();
            btn.setTag(R.id.tag_apk_flag, flag);
            bindClick(btn, mAppBean);
            int pos = (int) btn.getTag(R.id.BTN_POS);
            if(mAppBean.position!=pos){
                return;
            }

            switch (flag) {

                case DownloadFlag.INSTALLED:
                    btn.setText("打开");
                    break;

                case DownloadFlag.NORMAL:
                    btn.download();
                    //btn.setText("下载");
                    break;

                case DownloadFlag.STARTED:
                    btn.setProgress((int) event.getDownloadStatus().getPercentNumber());
                    break;

                case DownloadFlag.PAUSED:
                    btn.setProgress((int) event.getDownloadStatus().getPercentNumber());
                    btn.paused();
                    break;

                case DownloadFlag.COMPLETED: //已完成
                    btn.setText("安装");
                    //installApp(btn.getContext(),mAppBean);
                    break;
                case DownloadFlag.FAILED://下载失败
                    btn.setText("失败");
                    break;
                case DownloadFlag.DELETED: //已删除
                    break;
            }

        }
    }

    interface DownLoadAppApi {

        @GET("download/{id}")
        Observable<BaseBean<AppDownloadInfo>> getAppDownloadInfo(@Path("id") int id);
    }
}
