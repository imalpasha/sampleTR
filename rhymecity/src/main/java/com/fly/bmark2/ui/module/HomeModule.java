package com.fly.bmark2.ui.module;

import com.fly.bmark2.AppModule;
import com.fly.bmark2.ui.fragment.Homepage.HomeFragment;
import com.fly.bmark2.ui.presenter.HomePresenter;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;

@Module(
        injects = HomeFragment.class,
        addsTo = AppModule.class,
        complete = false
)
public class HomeModule {

    private final HomePresenter.HomeView homeView;

    public HomeModule(HomePresenter.HomeView homeView) {
        this.homeView = homeView;
    }

    //@Provides
    @Singleton
    HomePresenter provideHomePresenter(Bus bus) {
        return new HomePresenter(homeView, bus);
    }
}
