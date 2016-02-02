package com.oic.calcmarket.models;

/**
 * Created by khacpham on 2/2/16.
 */
public class BaseBillData {
    public static final int TYPE_BILL_HEADER = 0;
    public static final int TYPE_BILL_ITEM = 1;
    public static final int TYPE_BILL_TOTAL = 2;
    public static final int TYPE_BILL_FOOTER = 3;

    public int type = TYPE_BILL_ITEM;

    public BaseBillData(int type){
        this.type = type;
    }
}
