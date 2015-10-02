package com.fly.bmark2.ui.module;

import com.fly.bmark2.AppModule;
import com.fly.bmark2.ui.fragment.SplashScreenFragment;
import com.fly.bmark2.ui.presenter.HomePresenter;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = SplashScreenFragment.class,
        addsTo = AppModule.class,
        complete = false
)
public class SplashScreenModule {

    private final HomePresenter.HomeView homeView;

    public SplashScreenModule(HomePresenter.HomeView homeView) {
        this.homeView = homeView;
    }

    @Provides
    @Singleton
    HomePresenter provideHomePresenter(Bus bus) {
        return new HomePresenter(homeView, bus);
    }
}
