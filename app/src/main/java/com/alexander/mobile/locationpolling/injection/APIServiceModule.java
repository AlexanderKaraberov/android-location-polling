package com.alexander.mobile.locationpolling.injection;

import com.alexander.mobile.locationpolling.network.APIBaseUrl;
import com.alexander.mobile.locationpolling.network.APIService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by alexander on 12/7/15.
 */

@Module
public class APIServiceModule {

    @Provides
    @Singleton
    APIService provideApiService () {

        return new Retrofit.Builder()
                .baseUrl(new APIBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIService.class);

    }
}

