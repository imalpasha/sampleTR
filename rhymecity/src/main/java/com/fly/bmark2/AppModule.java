package com.fly.bmark2;

import com.fly.bmark2.api.ApiEndpoint;
import com.fly.bmark2.api.ApiRequestInterceptor;
import com.fly.bmark2.api.ApiService;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

@Module(
        injects = AppApplication.class
)
public class AppModule {

    private final String apiKey;

    public AppModule(String apiKey) {
        this.apiKey = apiKey;
    }

    @Provides
    @Singleton
    RequestInterceptor provideRequestInterceptor() {
        return new ApiRequestInterceptor(apiKey);
    }

    @Provides
    @Singleton
    Endpoint provideEndpoint() {
        return new ApiEndpoint();
    }

    @Provides
    @Singleton
    ApiService provideApiService(RequestInterceptor requestInterceptor, Endpoint endpoint) {
        return new RestAdapter.Builder()
                .setEndpoint(endpoint)
                .setRequestInterceptor(requestInterceptor)
                .build()
                .create(ApiService.class);
    }

    @Provides
    @Singleton
    Bus provideBus() {
        return new Bus();
    }
}
