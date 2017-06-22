package com.lxy.shop.ui.classify.constract;

import com.lxy.shop.common.base.BaseView;
import com.lxy.shop.ui.classify.bean.ClassifyBean;

import java.util.List;

/**
 * Created by lxy on 2017/6/21.
 */

public interface ClassifyConstract {

    interface ClassifyView extends BaseView {

        void onGetClassifySuccess(List<ClassifyBean> list);
    }
}
