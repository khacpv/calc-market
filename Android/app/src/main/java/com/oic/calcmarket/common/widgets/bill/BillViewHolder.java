package com.oic.calcmarket.common.widgets.bill;

import android.support.v7.widget.RecyclerView;

import com.oic.calcmarket.models.BaseBillData;

/**
 * Created by khacpham on 2/2/16.
 */
public class BillViewHolder extends RecyclerView.ViewHolder {

    public BillViewHolder(BillView billViewItem) {
        super(billViewItem);
    }

    public void setData(int position,BaseBillData item){
        ((BillView)itemView).setDataContext(position,item);
    }
}
