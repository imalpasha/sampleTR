package com.fly.bmark2.api;

import com.fly.bmark2.api.obj.Version;
import com.fly.bmark2.api.obj.tryObj;

import retrofit.Callback;
import retrofit.http.GET;

public interface ApiService {

    /*@GET("/words/{word}/rhymes")
    void getRhymes(@Path("word") String word, Callback<RhymesResponse> callback);

    @GET("/users/{user}")
    void getFeed2(@Path("user") String user, Callback<tryObj> callback);*/

    @GET("/apis/592715d9")
    void getGoogleSpreedSheetData(Callback<tryObj> callback);

    @GET("/apis/8891c88e")
    void getTagData(Callback<tryObj> callback);

    @GET("/apis/3701203e")
    void getVersion(Callback<Version> callback);


}
