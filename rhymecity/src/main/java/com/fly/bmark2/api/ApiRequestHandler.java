package com.fly.bmark2.api;

import android.util.Log;

import com.fly.bmark2.MainFragmentActivity;
import com.fly.bmark2.api.obj.Version;
import com.fly.bmark2.api.obj.tryObj;
import com.fly.bmark2.retroObj.GetVersion;
import com.fly.bmark2.retroObj.NeedUpdate;
import com.fly.bmark2.retroObj.UpdateDB;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import org.json.JSONArray;
import org.json.JSONObject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ApiRequestHandler  {

    private  Bus bus ;
    private  ApiService apiService ;

    private  Bus bus2 ;
    private  ApiService apiService2 ;


    public ApiRequestHandler(Bus bus, ApiService apiService) {
        this.bus = bus;
        this.apiService = apiService;
    }

    public ApiRequestHandler() {
        bus2 = bus;
        apiService2 = apiService;
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

    /*public void test(String test) {
        final String word = test;
        Log.e("word", word);
    }


*/

   /* @Subscribe
    public void getGoogleSpreedSheetData(final RhymesRequestedEvent event) {
        final String word = event.getWord();
        Log.e("word", word);
        apiService.getGoogleSpreedSheetData(new Callback<tryObj>() {

            @Override
            public void success(tryObj userDetailResponse, Response response) {

                Log.e("response1", response.toString());
                Log.e("response2", userDetailResponse.getStatus());

                bus.post(new UserRetrieveSuccess(userDetailResponse));
            }

            @Override
            public void failure(RetrofitError error) {
                bus.post(new RhymesFailureEvent(word));
            }
        });
    }*/



    @Subscribe
    public void getVersion(final GetVersion event) {

        apiService.getVersion(new Callback<Version>() {

            @Override
            public void success(Version userDetailResponse, Response response) {

                Log.e("response1", response.toString());

                JSONArray json = new JSONArray(userDetailResponse.getResult());
                ;
                for (int i = 0; i < json.length(); i++) {
                    JSONObject row = (JSONObject) json.opt(i);

                    String version = row.optString("Version");
                    String onPhoneVersion = MainFragmentActivity.getVersion();

                    if (!version.equals(onPhoneVersion)) {
                        bus.post(new NeedUpdate(version));

                    } else {
                        Log.e("Data Updated", "True");
                    }
                }

            }

            @Override
            public void failure(RetrofitError error) {
                //bus.post(new RhymesFailureEvent(word));
                Log.e("Failed", "True");
            }
        });
    }


    @Subscribe
    public void getTagData(final NeedUpdate event) {

        final String latestVersion = event.getVersion();

        apiService.getTagData(new Callback<tryObj>() {

            @Override
            public void success(tryObj userDetailResponse, Response response) {

                userDetailResponse.setVersion(latestVersion);
                bus.post(new UpdateDB(userDetailResponse));
            }

            @Override
            public void failure(RetrofitError error) {
                //bus.post(new RhymesFailureEvent(word));
                Log.e("Failed","TRUE");
            }
        });
    }

}
