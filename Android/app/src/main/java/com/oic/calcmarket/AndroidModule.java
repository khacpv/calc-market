package com.oic.calcmarket;

import android.content.Context;
import android.location.LocationManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by khacpham on 1/31/16.
 */
@Module
public class AndroidModule {

    private final MainApplication application;

    public AndroidModule(MainApplication application){
        this.application = application;
    }

    /**
     * Allow the application context to be injected but require that it be annotated with
     * {@link ForApplication @Annotation} to explicitly differentiate it from an activity context.
     */
    @Provides
    @Singleton
    @ForApplication
    Context provideApplicationContext(){
        return application;
    }

    @Provides @Singleton
    LocationManager provideLocationManager(){
        return (LocationManager)application.getSystemService(Context.LOCATION_SERVICE);
    }
}
