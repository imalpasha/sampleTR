package com.fly.bmark2.api;

import com.fly.bmark2.api.model.RhymesResponse;
import com.fly.bmark2.api.obj.tryObj;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface ApiService {

    @GET("/words/{word}/rhymes")
    void getRhymes(@Path("word") String word, Callback<RhymesResponse> callback);

    @GET("/users/{user}")
    void getFeed2(@Path("user") String user, Callback<tryObj> callback);
}
