package com.lxy.shop.ui.classify.presenter;

import com.blankj.utilcode.util.LogUtils;
import com.lxy.shop.common.base.BasePresenter;
import com.lxy.shop.common.rx.RxHttpResponse;
import com.lxy.shop.common.rx.observer.ErrorHandObserver;
import com.lxy.shop.common.rx.observer.ProgressObserver;
import com.lxy.shop.ui.classify.bean.ClassifyBean;
import com.lxy.shop.ui.classify.constract.ClassifyConstract;
import com.lxy.shop.ui.classify.model.ClassifyModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by lxy on 2017/6/21.
 */

public class ClassifyPresenter extends BasePresenter<ClassifyModel, ClassifyConstract.ClassifyView> {

    @Inject
    public ClassifyPresenter(ClassifyModel mModel, ClassifyConstract.ClassifyView mView) {
        super(mModel, mView);
    }

    public void getData() {

        mModel.getData()
                .compose(RxHttpResponse.<List<ClassifyBean>>handResult())
                .subscribe(new ProgressObserver<List<ClassifyBean>>(mContext,mView) {
                    @Override
                    public void onNext(List<ClassifyBean> list) {
                        mView.onGetClassifySuccess(list);
                    }
                });
    }

}
