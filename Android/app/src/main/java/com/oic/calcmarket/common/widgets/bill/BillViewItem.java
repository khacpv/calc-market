package com.oic.calcmarket.common.widgets.bill;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.oic.calcmarket.R;
import com.oic.calcmarket.common.widgets.edittext.CEditText;
import com.oic.calcmarket.common.widgets.edittext.CTextView;
import com.oic.calcmarket.common.widgets.edittext.OnKeyBackListener;
import com.oic.calcmarket.models.BillItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by khacpham on 1/11/16.
 */
public class BillViewItem extends BillView {

    private OnBillChangedListener changedListener;

    @Bind(R.id.btnDelete)
    ImageView btnDelete;

    @Bind(R.id.tvIndex)
    CTextView tvIndex;

    @Bind(R.id.tvTitle)
    CEditText tvTitle;

    @Bind(R.id.tvCost)
    CEditText tvCost;

    @Bind(R.id.tvQty)
    CTextView tvQty;

    @Bind(R.id.lyButton)
    LinearLayout lyButtons;

    @Bind(R.id.btnSub)
    ImageButton btnSub;

    @Bind(R.id.btnAdd)
    ImageButton btnAdd;

    public BillViewItem(Context context) {
        super(context);
        init();
    }

    public BillViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BillViewItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.bill_item, this, true);

        setLayoutParams(new BillView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        ButterKnife.bind(this);

        btnDelete.setVisibility(View.INVISIBLE);
        btnAdd.setVisibility(View.GONE);
        btnSub.setVisibility(View.GONE);

        final OnFocusChangeListener listener = new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                setFocusChange(hasFocus);
            }
        };

        OnKeyBackListener backListener = new OnKeyBackListener() {
            @Override
            public void onKeyBack() {
                setFocusChange(false);
            }
        };

        tvTitle.setOnFocusChangeListener(listener);
        tvCost.setOnFocusChangeListener(listener);
        tvQty.setOnFocusChangeListener(listener);

        tvTitle.setOnKeyBackListener(backListener);
        tvCost.setOnKeyBackListener(backListener);

        setFocusChange(false);
    }

    public void setDataContext(BillItem data){
        tvTitle.setText(data.name);
        tvCost.setText(data.cost + "");
        tvQty.setText(data.quantity);
        setFocusChange(false);
    }

    public void setFocusChange(boolean hasFocus){
        btnDelete.setVisibility(hasFocus ? View.VISIBLE : View.INVISIBLE);
        btnAdd.setVisibility(hasFocus ? View.VISIBLE : View.GONE);
        btnSub.setVisibility(hasFocus ? View.VISIBLE : View.GONE);
        if(hasFocus){
            tvQty.setVisibility(View.VISIBLE);
        }
        int quantity = 0;
        float cost = 0;
        try {
            quantity = Integer.parseInt(tvQty.getText().toString());
            if(!hasFocus && quantity == 1){
                tvQty.setVisibility(View.GONE);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            cost = Float.parseFloat(tvCost.getText().toString());
        }catch (Exception e){
            e.printStackTrace();
        }

        if(changedListener!=null){
            changedListener.change(cost, quantity);
        }
    }

    @OnClick({R.id.btnAdd,R.id.btnSub,R.id.btnDelete})
    public void btnClicked(View view){
        switch (view.getId()){
            case R.id.btnAdd:
                changedListener.add(this);
                break;
            case R.id.btnSub:
                changedListener.sub(this);
                break;
            case R.id.btnDelete:
                changedListener.remove(this);
                break;
        }
    }

    public void setOnBillChangedListener(OnBillChangedListener listener){
        this.changedListener = listener;
    }

    public interface OnBillChangedListener {
        void remove(BillViewItem item);
        void change(float cost,int quantity);
        void add(BillViewItem item);
        void sub(BillViewItem item);
    }

}