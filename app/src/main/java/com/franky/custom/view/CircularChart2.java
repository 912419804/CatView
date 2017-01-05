package com.franky.custom.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by franky on 2016/12/31.
 * 绘制图形基本案例
 */

public class CircularChart2 extends View {

    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    private Context mContext;
    private Paint mPaint;

    public CircularChart2(Context context) {
        this(context, null);
    }

    public CircularChart2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircularChart2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        this.mContext = getContext();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeJoin(Paint.Join.MITER);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeMiter(10);
        mPaint.setStrokeWidth(20);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        绘制整个布局的背景
//        canvas.drawColor(Color.RED);
//        绘制一个点
//        canvas.drawPoint(100,0,mPaint);
//        绘制多个点
//        canvas.drawPoints(new float[]{200, 50, 200, 100, 200, 200, 200, 300}, mPaint);
//        绘制多个点,可以选择绘制个数
//        canvas.drawPoints(new float[]{200, 50, 200, 100, 200, 150, 200, 200},2,2 ,mPaint);
//        绘制矩形，坐标为左上角和右下角
//        Rect rect = new Rect(50,50,300,300);
//        canvas.drawRect(rect,mPaint);
//        绘制圆角矩形
//        RectF rect = new RectF(50,50,200,300);
//        canvas.drawRoundRect(rect,10,10,mPaint);
//        绘制椭圆,其实就是矩形的内切圆
//        RectF rect = new RectF(50,50,200,300);
//        canvas.drawOval(rect,mPaint);
//        绘制圆
//        canvas.drawCircle(300,300,50,mPaint);
//        绘制线段
//        canvas.drawLine(0,0,300,300,mPaint);
//        绘制扇形
//        RectF rect = new RectF(50,50,200,200);
//        canvas.drawArc(rect,200,90,true,mPaint);
//        绘制文字
//        canvas.drawText("hello world",100,100,mPaint);
//        canvas.drawText("hello world",2,5,100,100,mPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
