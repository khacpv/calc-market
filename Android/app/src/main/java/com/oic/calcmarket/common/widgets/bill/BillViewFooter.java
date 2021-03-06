package com.oic.calcmarket.common.widgets.bill;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.oic.calcmarket.R;
import com.oic.calcmarket.models.BaseBillData;

import butterknife.ButterKnife;

/**
 * Created by khacpham on 2/2/16.
 */
public class BillViewFooter extends BillView {

    public BillViewFooter(Context context) {
        super(context);
        init(null);
    }

    public BillViewFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public BillViewFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @Override
    public void setDataContext(int position, BaseBillData data) {

    }

    private void init(AttributeSet attrs){
        LayoutInflater.from(getContext()).inflate(R.layout.bill_footer, this, true);
        setLayoutParams(new BillView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ButterKnife.bind(this);
    }
}
