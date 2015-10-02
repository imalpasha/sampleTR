package com.fly.bmark2.ui.presenter;

import com.fly.bmark2.retroObj.RhymesRequestedEvent;
import com.squareup.otto.Bus;

public class RegisterPresenter {

    public interface RegisterView {

    }

    private final RegisterView view;
    private final Bus bus;

    public RegisterPresenter(RegisterView view, Bus bus) {
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
}
