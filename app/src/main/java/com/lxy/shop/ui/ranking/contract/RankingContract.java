package com.lxy.shop.ui.ranking.contract;

import com.lxy.shop.common.base.BaseView;
import com.lxy.shop.common.rx.PageBean;
import com.lxy.shop.ui.recommend.AppBean;

/**
 * Created by lxy on 2017/6/16.
 */

public interface RankingContract {

    interface AppinfoView extends BaseView {

        void showResult(PageBean<AppBean> page);

        void onLoadMoreComplete();
    }
}
