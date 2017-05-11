package com.lxy.shop.ui.recommend.contract;

import com.lxy.shop.common.base.BaseView;
import com.lxy.shop.ui.recommend.AndroidBean;

/**
 * Created by lxy on 2017/5/11.
 */

public interface RecommendContract {

    interface View extends BaseView{

        void showResust(AndroidBean bean);
        void showNoData();
        void showError(String msg);
    }
}
