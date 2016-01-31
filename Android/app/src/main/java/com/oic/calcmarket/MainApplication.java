package com.oic.calcmarket;

import android.app.Application;
import android.location.LocationManager;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by khacpham on 1/31/16.
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerMainApplication_ApplicationComponent.builder()
                .androidModule(new AndroidModule(this))
                .build();
        component.inject(this);
    }

    @Singleton
    @Component(modules = AndroidModule.class)
    public interface ApplicationComponent {
        void inject(MainApplication application);
        void inject(BaseActivity activity);
        void inject(MainActivity activity);
    }

    @Inject
    LocationManager locationManager;

    private ApplicationComponent component;

    public ApplicationComponent component(){
        return component;
    }
}
