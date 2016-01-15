package com.oic.calcmarket.common.views.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by khacpham on 1/12/16.
 */
public class TriangleView extends LinearLayout {

    RectF bound = new RectF();

    Path clipToPath;

    public TriangleView(Context context) {
        super(context);
        init();
    }

    public TriangleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        setWillNotDraw(false);
        clipToPath = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bound.left = 0;
        bound.top = 0;
        bound.right = w;
        bound.bottom = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        clipToPath.moveTo(bound.centerX(), 0);
        clipToPath.lineTo(bound.right, bound.bottom);
        clipToPath.lineTo(0, bound.bottom);
        clipToPath.close();
        canvas.clipPath(clipToPath);

    }
}
