package com.oic.calcmarket.models;

/**
 * Created by khacpham on 2/2/16.
 */
public class BillItem extends BaseBillData{
    public String name;

    public float cost;

    public int quantity;

    public String thumb;

    public BillItem(int type) {
        super(type);
    }

    public float getTotal(){
        return cost*quantity;
    }
}
