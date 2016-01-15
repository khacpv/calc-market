package com.oic.calcmarket.common.network;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by khacpham on 1/12/16.
 */
public class BarcodeMng {
    static Api service;

    public BarcodeMng(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.upcdatabase.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(Api.class);
    }

    public static Api getApi(){
        return service;
    }

    static {
        new BarcodeMng();
    }
}
