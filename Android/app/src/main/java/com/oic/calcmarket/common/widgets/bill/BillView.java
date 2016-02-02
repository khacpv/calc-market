package com.oic.calcmarket.common.widgets.bill;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.oic.calcmarket.models.BaseBillData;

/**
 * Created by khacpham on 2/2/16.
 */
public class BillView extends FrameLayout {
    public BillView(Context context) {
        super(context);
    }

    public BillView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BillView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setDataContext(BaseBillData data) {

    }
}
