package com.lxy.shop.ui.recommend.contract;

import com.lxy.shop.common.base.BaseView;
import com.lxy.shop.common.rx.PageBean;
import com.lxy.shop.ui.recommend.AndroidBean;
import com.lxy.shop.ui.recommend.AppBean;

/**
 * Created by lxy on 2017/5/11.
 */

public interface RecommendContract {

    interface View extends BaseView{

        void showResust(PageBean<AppBean> bean);
        void showNoData();
        void showError(String msg);
    }
}
