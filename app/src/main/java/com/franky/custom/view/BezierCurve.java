package com.franky.custom.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2017/1/3.
 * 贝塞尔曲线练习
 */

public class BezierCurve extends View {

    private float centerX;
    private float centerY;
    private PointF startP;
    private PointF endP;
    private PointF controlP;
    private Path mPath;
    private Paint mPaint;

    public BezierCurve(Context context) {
        this(context, null);
    }

    public BezierCurve(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierCurve(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        centerX = w / 2;
        centerY = h / 2;
        startP = new PointF(centerX - 100, centerY);
        endP = new PointF(centerX + 100, centerY);
        controlP = new PointF(centerX, centerY - 50);
        mPath = new Path();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        controlP.set(x,y);
        mPath.reset();
        mPath.moveTo(x,y);
        mPath.lineTo(startP.x,startP.y);
        mPath.moveTo(x,y);
        mPath.lineTo(endP.x,endP.y);
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.DKGRAY);
        canvas.drawPath(mPath,mPaint);
        mPath.reset();//必须进行reset，否则颜色不会更新
        mPaint.setColor(Color.RED);
        mPath.moveTo(startP.x,startP.y);
        mPath.quadTo(controlP.x,controlP.y,endP.x,endP.y);
        canvas.drawPath(mPath,mPaint);
    }
}
