package com.lxy.shop.ui.download.downloaded;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.lxy.shop.R;
import com.lxy.shop.common.apk.AndroidApk;
import com.lxy.shop.common.base.BaseFragment;
import com.lxy.shop.common.constant.Constant;
import com.lxy.shop.databinding.FragmentDownloadedBinding;
import com.lxy.shop.di.component.AppComponent;
import com.lxy.shop.ui.download.downloaded.adapter.DownLoadedAdapter;
import com.orhanobut.hawk.Hawk;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import zlc.season.rxdownload2.entity.DownloadRecord;

/**
 * Created by lxy on 2017/6/30.
 */

public class DownLoadedFragment extends BaseFragment {

    private FragmentDownloadedBinding mBinding;
    private DownLoadedAdapter mAdapter;
    private ArrayList<AndroidApk> mList;

    @Override
    protected void visiableToUser() {

    }

    @Override
    protected void firstVisiableToUser() {
        LogUtils.d("download=======loaded");
        initData();
    }

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_downloaded;
    }

    @Override
    public void initChildBinding() {
        mBinding = (FragmentDownloadedBinding) mChildBinding;
    }

    @Override
    public void onEmptyClick(View view) {

    }


    public void initData() {

        mList = new ArrayList<>();

        final String dir = Hawk.get(Constant.APK_DOWNLOAD_DIR, "");

        Observable.create(new ObservableOnSubscribe<List<AndroidApk>>() {

            @Override
            public void subscribe(ObservableEmitter<List<AndroidApk>> e) throws Exception {
                e.onNext(scanApks(dir));
                e.onComplete();
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<AndroidApk>>() {
            @Override
            public void accept(@NonNull List<AndroidApk> androidApks) throws Exception {

                mAdapter = new DownLoadedAdapter(R.layout.list_item_down_loaded, androidApks);
                mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                mBinding.recyclerView.setAdapter(mAdapter);
            }
        });

    }

    public List<AndroidApk> scanApks(String dir) {

        File file = new File(dir);

        if (!file.isDirectory()) {

            throw new RuntimeException("is not Dir");
        }

        File[] apks = file.listFiles(new FileFilter() {

            @Override
            public boolean accept(File f) {

                if (f.isDirectory()) {
                    return false;
                }

                return f.getName().endsWith(".apk");
            }
        });

        List<AndroidApk> androidApks = new ArrayList<>();

        for (File apk : apks) {

            AndroidApk androidApk = AndroidApk.read(getContext(), apk.getPath());
            if (androidApk != null)
                androidApks.add(androidApk);
        }

        return androidApks;

    }

}
