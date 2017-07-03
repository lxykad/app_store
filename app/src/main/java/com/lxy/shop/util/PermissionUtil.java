package com.lxy.shop.util;

import android.app.Activity;

import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Observable;



public class PermissionUtil {

    public static Observable<Boolean> requestPermisson(Activity activity, String permission){

        RxPermissions rxPermissions =  new RxPermissions(activity);

        return rxPermissions.request(permission);
    }

}
