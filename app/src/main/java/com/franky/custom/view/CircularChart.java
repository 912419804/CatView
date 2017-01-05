package com.franky.custom.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.franky.custom.bean.Pie;

/**
 * Created by franky on 2016/12/31.
 * 绘制图形基本案例
 */

public class CircularChart extends View {

    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    private Context mContext;
    private Pie[] mPies;
    private Paint mPaint;
    private RectF mRectF;
    private int mWidth;
    private int mHeight;

    public CircularChart(Context context) {
        this(context, null);
    }

    public CircularChart(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircularChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        this.mContext = getContext();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(6);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeMiter(10);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mWidth = w;
        this.mHeight = h;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth/2,mHeight/2);
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        RectF rectF = new RectF(-200, -200, 200, 200);
        canvas.drawRect(rectF,mPaint);
        for (int i=0;i<40;i++){
            mPaint.setColor(mColors[i%mColors.length]);
            canvas.scale(0.9f,0.9f);
            canvas.drawRect(rectF,mPaint);
        }
    }

}
