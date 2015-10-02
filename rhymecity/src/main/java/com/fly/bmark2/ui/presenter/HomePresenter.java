package com.fly.bmark2.ui.presenter;

import android.util.Log;

import com.fly.bmark2.api.obj.tryObj;
import com.fly.bmark2.retroObj.GetVersion;
import com.fly.bmark2.retroObj.RhymesRequestedEvent;
import com.fly.bmark2.retroObj.RhymesSuccessEvent;
import com.fly.bmark2.retroObj.UpdateDB;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

public class HomePresenter {

    public interface HomeView {

        void saveIntoDb(tryObj obj);
    }

    private final HomeView view;
    private final Bus bus;

    public HomePresenter(HomeView view, Bus bus) {
        this.view = view;
        this.bus = bus;
    }

    public void onResume() {
        bus.register(this);
    }

    public void onPause() {
        bus.unregister(this);
    }

    public void onRhymesForWordRequested(String word) {
        bus.post(new RhymesRequestedEvent(word));
    }

    @Subscribe
    public void onUserSuccess(UpdateDB event) {

        tryObj obj = event.getUserObj();
        view.saveIntoDb(obj);

    }

    //bus.post(new UserRetrieveSuccess(userDetailResponse));

    public void onRequestVersion() {
        bus.post(new GetVersion());

    }

    @Subscribe
    public void onRequestGoogleData(RhymesSuccessEvent event) {


            Log.e("Y","Y");

    }





}
