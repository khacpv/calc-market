package com.oic.calcmarket;

import android.app.Application;
import android.location.LocationManager;

import com.oic.calcmarket.screens.bill.BillActivity;

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
    }
}
