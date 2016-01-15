package com.oic.calcmarket.common.views.edittext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import com.oic.calcmarket.R;

public class CTextView extends TextView {

    private Rect mRect;
    private Paint mPaint;

    private String assetFont = "";

    public CTextView(Context context) {
        super(context);
        init(null);
    }

    public CTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if(attrs!=null){
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CEditText);
            assetFont = a.getString(R.styleable.CEditText_assets_font);
            if(!TextUtils.isEmpty(assetFont)){
                Typeface font = Typeface.createFromAsset(getContext().getAssets(), assetFont);
                this.setTypeface(font);
            }
        }

        mRect = new Rect();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(0x800000FF);
    }

    @Override
    public void setTypeface(Typeface tf, int style) {
        if(!TextUtils.isEmpty(assetFont)){
            tf = Typeface.createFromAsset(getContext().getAssets(), assetFont);
        }
        super.setTypeface(tf, style);
    }

    @Override
    public void setTypeface(Typeface tf) {
        if(!TextUtils.isEmpty(assetFont)){
            tf = Typeface.createFromAsset(getContext().getAssets(), assetFont);
        }
        super.setTypeface(tf);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        int count = getLineCount();
//        Rect r = mRect;
//        Paint paint = mPaint;
//
//        for (int i = 0; i < count; i++) {
//            int baseline = getLineBounds(i, r);
//            canvas.drawLine(r.left, baseline + 7, r.right, baseline + 7, paint);
//        }

        super.onDraw(canvas);
    }
}