package com.franky.custom.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.franky.custom.bean.Pie;

/**
 * Created by franky on 2016/12/31.
 * 绘制图形基本案例
 */

public class CircularChart3 extends View {

    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    private Context mContext;
    private Pie[] mPies;
    private Paint mPaint;
    private RectF mRectF;
    private int mWidth;
    private int mHeight;
    private boolean isFirstDraw = true;

    public CircularChart3(Context context) {
        this(context, null);
    }

    public CircularChart3(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircularChart3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        this.mContext = getContext();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setColor(Color.RED);
        mPaint.setStrokeJoin(Paint.Join.MITER);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeMiter(10);
        mPaint.setStrokeWidth(20);
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
        float r = (float) (mWidth * 0.8 / 2);
        mRectF = new RectF(-r, -r, r, r);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
        if (mPies == null || mPies.length <= 0) {
            return;
        }
        int length = mPies.length;
        float startAngle = -90f;
        for (int i = 0; i < length; i++) {
            mPaint.setColor(mColors[i % mColors.length]);
            canvas.drawArc(mRectF, startAngle, mPies[i].angle, true, mPaint);
            startAngle += mPies[i].angle;
        }

    }


    public void setData(Pie[] pies) {
        this.mPies = pies;
        initData();
        invalidate();
    }

    private void initData() {
        if (mPies == null || mPies.length <= 0) {
            return;
        }
//        计算每个数据所占的比例和角度
        float count = 0;
        for (Pie pie : mPies) {
            count += pie.num;
        }
        if (count <= 0) {
            return;
        }
        for (Pie pie : mPies) {
            pie.angle = 360f * (pie.num / count);
        }

    }

}
