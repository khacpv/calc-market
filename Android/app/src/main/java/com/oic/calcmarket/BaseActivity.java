package com.oic.calcmarket;

import android.location.LocationManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

/**
 * Created by khacpham on 1/31/16.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApplication) getApplication()).component().inject(this);
    }
}
