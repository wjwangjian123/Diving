package com.example.edive.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.example.edive.R;

public class MyRoundLayout extends View {
    private PorterDuffXfermode mPorterDuffXfermode;
    private RectF srcRect;
    private Context mContext;
    private int angle;
    private int edgeColor;
    public MyRoundLayout(Context context) {
        super(context);
        mContext = context;
    }
    public MyRoundLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPorterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.XOR);
        mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FilletView);
        angle = typedArray.getDimensionPixelOffset(R.styleable.FilletView_angle, 0);
        edgeColor = typedArray.getColor(R.styleable.FilletView_edgeColor, 00000000);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.TRANSPARENT);
        Paint _paint = new Paint();
        int saveCount = canvas.saveLayer(srcRect, _paint, Canvas.ALL_SAVE_FLAG);
        _paint.setColor(edgeColor);
        canvas.drawRoundRect(srcRect, angle, angle, _paint);
        _paint.setXfermode(mPorterDuffXfermode);
        canvas.drawRect(srcRect, _paint);
        _paint.setXfermode(null);
        canvas.restoreToCount(saveCount);
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        srcRect = new RectF(0, 0, w, h);
    }
}
