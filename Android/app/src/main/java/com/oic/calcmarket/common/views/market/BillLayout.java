package com.oic.calcmarket.common.views.market;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.oic.calcmarket.R;
import com.oic.calcmarket.common.views.edittext.CEditText;
import com.oic.calcmarket.common.views.edittext.CTextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by khacpham on 1/11/16.
 */
public class BillLayout extends FrameLayout {

    @Bind(R.id.btnDelete)
    ImageView btnDelete;

    @Bind(R.id.tvIndex)
    CTextView tvIndex;

    @Bind(R.id.tvTitle)
    CEditText tvTitle;

    @Bind(R.id.tvCost)
    CEditText tvCost;

    public BillLayout(Context context) {
        super(context);
        init();
    }

    public BillLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BillLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.market_item, this, true);

        ButterKnife.bind(this);

        btnDelete.setVisibility(View.INVISIBLE);

        OnFocusChangeListener listener = new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                btnDelete.setVisibility(hasFocus?View.VISIBLE:View.INVISIBLE);
            }
        };

        tvTitle.setOnFocusChangeListener(listener);
        tvCost.setOnFocusChangeListener(listener);
    }
}
