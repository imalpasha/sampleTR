package com.fly.bmark2.api;

import retrofit.Endpoint;

public class ApiEndpoint implements Endpoint {

    @Override
    public String getUrl() {
        return "https://api.github.com";
    }

    @Override
    public String getName() {
        return "Production Endpoint";
    }
    //
}
