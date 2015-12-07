package com.alexander.mobile.locationpolling.injection;

import com.alexander.mobile.locationpolling.tasks.LocationPollingTask;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by alexander on 12/7/15.
 */
@Singleton
@Component(modules = APIServiceModule.class)

public interface APIServiceComponent {

    void inject (LocationPollingTask task);
}
