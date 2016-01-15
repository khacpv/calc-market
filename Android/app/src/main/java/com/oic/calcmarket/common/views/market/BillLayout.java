package com.oic.calcmarket.common.views.market;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.oic.calcmarket.R;
import com.oic.calcmarket.common.views.edittext.CEditText;
import com.oic.calcmarket.common.views.edittext.CTextView;
import com.oic.calcmarket.common.views.edittext.OnKeyBackListener;
import com.oic.calcmarket.models.BarcodeRsp;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by khacpham on 1/11/16.
 */
public class BillLayout extends FrameLayout {

    private OnBillChangedListener changedListener;

    float cost = 0.0f;

    int quantity = 1;

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

        try {
            quantity = Integer.parseInt(tvQty.getText().toString());
            setFocusChange(false);
        }catch (Exception e){

        }
    }

    public void setDataContext(BarcodeRsp response){
        tvTitle.setText(response.itemname);
        quantity = 1;
        setFocusChange(false);
    }

    public void reset(){
        tvTitle.setText("Untitled");
        cost = 0;
        tvCost.setText(cost + "");
        quantity = 1;
        tvQty.setText(quantity + "");

        setFocusChange(false);
    }

    public void setFocusChange(boolean hasFocus){
        btnDelete.setVisibility(hasFocus ? View.VISIBLE : View.INVISIBLE);
        btnAdd.setVisibility(hasFocus ? View.VISIBLE : View.GONE);
        btnSub.setVisibility(hasFocus ? View.VISIBLE : View.GONE);
        if(hasFocus){
            tvQty.setVisibility(View.VISIBLE);
        }
        try {
            quantity = Integer.parseInt(tvQty.getText().toString());
            if(!hasFocus && quantity == 1){
                tvQty.setVisibility(View.GONE);
            }
        }catch (Exception e){

        }
        try{
            cost = Float.parseFloat(tvCost.getText().toString());
        }catch (Exception e){

        }

        if(changedListener!=null){
            changedListener.change(cost,quantity);
        }
    }

    @OnClick(R.id.btnDelete)
    public void deleteItem(View view){
        ((ViewGroup)getParent()).removeView(this);
        if(changedListener != null){
            changedListener.remove((ViewGroup) getParent(), this);
        }
    }

    @OnClick({R.id.btnAdd,R.id.btnSub})
    public void btnClicked(View view){
        switch (view.getId()){
            case R.id.btnAdd:
                quantity++;
                break;
            case R.id.btnSub:
                quantity--;
                quantity = Math.max(1,quantity);
                break;
        }
        tvQty.setVisibility(View.VISIBLE);
        tvQty.setText(quantity + "");
    }

    public float getCost(){
        return cost*quantity;
    }

    public void setOnBillChangedListener(OnBillChangedListener listener){
        this.changedListener = listener;
    }

    public interface OnBillChangedListener {
        void remove(ViewGroup parent,BillLayout item);
        void change(float cost,int quantity);
    }
}
