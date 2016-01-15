package com.oic.calcmarket.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khacpham on 1/12/16.
 */
public class BarcodeRsp {

    public boolean valid;
    public String number;
    public String itemname;
    public String alias;

    public String description;

    @SerializedName("avg_price")
    public float avgPrice; // usd

    @SerializedName("rate_up")
    public int rateUp;

    @SerializedName("rate_down")
    public int rateDown;
}
