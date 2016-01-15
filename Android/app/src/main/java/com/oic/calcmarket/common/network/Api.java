package com.oic.calcmarket.common.network;

import com.oic.calcmarket.models.BarcodeRsp;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;


/**
 * Created by khacpham on 1/12/16.
 */
public interface Api {

    @GET("json/{apiKey}/{code}")
    Call<BarcodeRsp> findProduct(@Path("apiKey") String apiKey,@Path("code") String code);

}
