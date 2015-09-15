package com.fly.bmark2.ui.presenter;

import com.fly.bmark2.rhymes.RhymesRequestedEvent;
import com.squareup.otto.Bus;

public class HomePresenter {

    public interface HomeView {

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

    /*@Subscribe
    public void onRhymesSuccess(RhymesSuccessEvent event) {
        view.hideLoadingIndicator();

        List<String> rhymeList = event.getRhymes();
        if (rhymeList.isEmpty()) {
            view.showNoRhymesFoundError(event.getWord());
            Log.e("FAILED","Y");
        } else {
            view.goToRhymesViewWithRhymes(event.getWord(), rhymeList);
            Log.e("FAILED","N");

        }
    }*/

   /* @Subscribe
    public void onUserSuccess(UserRetrieveSuccess event) {
        view.hideLoadingIndicator();

        tryObj obj = event.getUserObj();
        view.displayUserInfo(obj);
        Log.e("UserName",obj.getLogin());
    }



    @Subscribe
    public void onRhymesFailure(RhymesFailureEvent event) {
        view.hideLoadingIndicator();
        view.showRetrieveRhymesError();
    }*/
}
