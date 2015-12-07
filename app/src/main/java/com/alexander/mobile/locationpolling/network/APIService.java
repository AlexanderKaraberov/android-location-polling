package com.alexander.mobile.locationpolling.network;

import retrofit.Call;
import retrofit.Response;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by alexander on 12/7/15.
 */
public interface APIService {

    @POST("/user/updateUserGps")
    Call<PollingResponse> updateLocationSynchronously(@Body SendLocationRequest body);

}
