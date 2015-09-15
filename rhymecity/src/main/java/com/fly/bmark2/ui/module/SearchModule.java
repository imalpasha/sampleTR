package com.fly.bmark2.ui.module;

import com.fly.bmark2.AppModule;
import com.fly.bmark2.ui.fragment.SearchFragment;
import com.fly.bmark2.ui.presenter.SearchPresenter;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = SearchFragment.class,
        addsTo = AppModule.class,
        complete = false
)
public class SearchModule {

    private final SearchPresenter.SearchView searchView;

    public SearchModule(SearchPresenter.SearchView searchView) {
        this.searchView = searchView;
    }

    @Provides
    @Singleton
    SearchPresenter provideSearchPresenter(Bus bus) {
        return new SearchPresenter(searchView, bus);
    }
}
