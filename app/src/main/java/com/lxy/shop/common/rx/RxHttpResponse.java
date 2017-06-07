package com.lxy.shop.common.rx;

import com.lxy.shop.common.exception.ApiException;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by lxy on 2017/5/20.
 * 请求结果预处理 transformer 转换类
 */

public class RxHttpResponse {

    /*public static <T> Observable.Transformer<BaseBean<T>, T> handResult() {

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

                                    } catch (Exception e) {
                                        subscriber.onError(e);
                                    }
                                }
                            });

                        } else {//请求失败
                            return Observable.error(new ApiException(tBaseBean.status, tBaseBean.message));
                        }
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };

    }*/


    /**
     * rxjava2 写法
     * @param <T>
     * @return
     */

    public static <T> ObservableTransformer<BaseBean<T>,T> handResult(){

        return new ObservableTransformer<BaseBean<T>, T>() {
            @Override
            public ObservableSource<T> apply(io.reactivex.Observable<BaseBean<T>> upstream) {

                return upstream.flatMap(new Function<BaseBean<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(@NonNull final BaseBean<T> tBaseBean) throws Exception {

                        if (tBaseBean.isStatusOk()) {//请求成功

                            return io.reactivex.Observable.create(new ObservableOnSubscribe<T>() {
                                @Override
                                public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                                    emitter.onNext(tBaseBean.data);
                                    emitter.onComplete();
                                }
                            });

                        } else {//请求失败

                           return io.reactivex.Observable.error(new ApiException(tBaseBean.status,tBaseBean.message));
                        }

                    }
                }).subscribeOn(io.reactivex.schedulers.Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
