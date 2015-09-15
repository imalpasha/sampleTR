package com.fly.bmark2.api;

import android.util.Log;

import com.fly.bmark2.api.obj.tryObj;
import com.fly.bmark2.rhymes.RhymesFailureEvent;
import com.fly.bmark2.rhymes.RhymesRequestedEvent;
import com.fly.bmark2.rhymes.UserRetrieveSuccess;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ApiRequestHandler {

    private final Bus bus;
    private final ApiService apiService;

    public ApiRequestHandler(Bus bus, ApiService apiService) {
        this.bus = bus;
        this.apiService = apiService;
    }

    /*@Subscribe
    public void onRhymesRequested(final RhymesRequestedEvent event) {
        final String word = event.getWord();

        apiService.getRhymes(word, new Callback<RhymesResponse>() {
            @Override
            public void success(RhymesResponse rhymesResponse, Response response) {
                bus.post(new RhymesSuccessEvent(word, rhymesResponse.getRhymesObject().getAll()));
            }

            @Override
            public void failure(RetrofitError error) {
                bus.post(new RhymesFailureEvent(word));
            }
        });
    }*/

    @Subscribe
    public void onInfoRequest(final RhymesRequestedEvent event) {
        final String word = event.getWord();

        apiService.getFeed2(word, new Callback<tryObj>() {

        //apiService.getFeed(word, new Callback<UserDetailResponse>() {
            @Override
            public void success(tryObj userDetailResponse, Response response) {
                Log.e("response1", response.toString());
                Log.e("response2",userDetailResponse.toString());
                Log.e("response3", userDetailResponse.getLogin());

                bus.post(new UserRetrieveSuccess(userDetailResponse));
            }

            @Override
            public void failure(RetrofitError error) {
                bus.post(new RhymesFailureEvent(word));
            }
        });
    }
}
