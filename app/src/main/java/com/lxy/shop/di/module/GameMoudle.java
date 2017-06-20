package com.lxy.shop.di.module;

import com.lxy.shop.data.api.ApiService;
import com.lxy.shop.ui.game.GameModel;
import com.lxy.shop.ui.game.constract.GameConstract;
import com.lxy.shop.ui.ranking.RankingModel;
import com.lxy.shop.ui.ranking.contract.RankingContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lxy on 2017/6/16.
 */

@Module
public class GameMoudle {

    public GameConstract.AppinfoView mView;

    public GameMoudle(GameConstract.AppinfoView view){
        mView = view;
    }

    @Provides
    public GameConstract.AppinfoView provideView(){
        return mView;
    }

    @Provides
    public GameModel provideMoudel(ApiService apiService){

        return new GameModel(apiService);
    }

}
