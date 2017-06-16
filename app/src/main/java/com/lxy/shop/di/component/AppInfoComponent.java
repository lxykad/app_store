package com.lxy.shop.di.component;

import com.lxy.shop.di.module.AppInfoMoudle;
import com.lxy.shop.di.scope.ActivityScope;
import com.lxy.shop.ui.game.GameFragment;
import com.lxy.shop.ui.ranking.RankingFragment;

import dagger.Component;

/**
 * Created by lxy on 2017/6/16.
 */

@ActivityScope
@Component(modules = AppInfoMoudle.class,dependencies = AppComponent.class)
public interface AppInfoComponent {

    void injectRankFragment(RankingFragment fragment);
    //void injectGameFragment(GameFragment fragment);
}
