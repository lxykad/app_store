package com.lxy.shop.common.rx;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by lxy on 2017/5/20.
 * 请求结果预处理 transformer 转换类
 */

public class RxHttpResponse {

    public static <T> Observable.Transformer<BaseBean<T> ,T> handResult(){

        return new Observable.Transformer<BaseBean<T>, T>() {
            @Override
            public Observable<T> call(Observable<BaseBean<T>> baseBeanObservable) {

                return baseBeanObservable.flatMap(new Func1<BaseBean<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(final BaseBean<T> tBaseBean) {

                        if (tBaseBean.isStatusOk()) {//请求成功

                            return Observable.create(new Observable.OnSubscribe<T>() {
                                @Override
                                public void call(Subscriber<? super T> subscriber) {

                                    try {

                                        subscriber.onNext(tBaseBean.data);
                                        subscriber.onCompleted();

                                    }catch (Exception e){
                                        subscriber.onError(e);
                                    }
                                }
                            });

                        }else {//请求失败
                            return Observable.error(new Exception());
                        }
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };

    }
}
