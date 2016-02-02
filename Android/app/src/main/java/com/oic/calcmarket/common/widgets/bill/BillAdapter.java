package com.oic.calcmarket.common.widgets.bill;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.oic.calcmarket.models.BaseBillData;

import java.util.List;

/**
 * Created by khacpham on 2/2/16.
 */
public class BillAdapter extends RecyclerView.Adapter<BillViewHolder> {

    List<BaseBillData> lists;
    Context context;
    BillViewItem.OnBillChangedListener listener;

    public BillAdapter(Context context,List<BaseBillData> data,BillViewItem.OnBillChangedListener listener){
        this.lists = data;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public BillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BillView view = null;
        if(viewType == BaseBillData.TYPE_BILL_HEADER){
            view = new BillViewHeader(context);
        }else if(viewType == BaseBillData.TYPE_BILL_ITEM){
            view = new BillViewItem(context);
            ((BillViewItem)view).setOnBillChangedListener(listener);
        }else if(viewType == BaseBillData.TYPE_BILL_TOTAL){
            view = new BillViewTotal(context);
        }else if(viewType == BaseBillData.TYPE_BILL_FOOTER){
            view = new BillViewFooter(context);
        }
        BillViewHolder holder = new BillViewHolder(view);
        holder.setIsRecyclable(false);  // fix Scrapped or attached views may not be recycled
        return holder;
    }

    @Override
    public void onBindViewHolder(BillViewHolder holder, int position) {
        holder.setData(position,lists.get(position));
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    @Override
    public int getItemViewType(int position) {
        return lists.get(position).type;
    }
}
