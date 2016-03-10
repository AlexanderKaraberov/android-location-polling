package com.alexander.mobile.locationpolling.network;

import com.squareup.okhttp.HttpUrl;

import retrofit.BaseUrl;

/**
 * Created by alexander on 3/10/16.
 */

public class APIBaseUrl implements BaseUrl {

    private final String DEVELOPMENT_HOST= "www.dev.userloc";
    private final String PRODUCTION_HOST = "www.prod.userloc";


    @Override
    public HttpUrl url() {

        //Specify your api url here
        return new HttpUrl.Builder()
                .scheme("https")
                .host(DEVELOPMENT_HOST)
                .addPathSegment("api")
                .build();
    }
}
