package com.lxy.shop.di.component;

import com.lxy.shop.di.module.GameMoudle;
import com.lxy.shop.di.scope.ActivityScope;
import com.lxy.shop.ui.game.GameFragment;

import dagger.Component;

/**
 * Created by lxy on 2017/6/20.
 */

@ActivityScope
@Component(modules = GameMoudle.class, dependencies = AppComponent.class)
public interface GameComponent {

    void injectGameFragment(GameFragment fragment);
}
