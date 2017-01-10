package com.franky.custom.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/6.
 * 折线图
 */

public class LineChart extends View {

    /**
     * 每天小时
     **/
    private int[] hours = new int[]{1, 2, 3, 4, 5, 6, 7};
    /**
     * 温度集合 范围10-30度
     **/
    private int[] temperatures = new int[]{15, 22, 19, 26, 27, 20, 13};

    private Paint mPaint = new Paint();

    private Path xPath = new Path();
    private Path yPath = new Path();
    private Path linePath = new Path();

    ArrayList<PointF> points = new ArrayList<>();

    float startX;
    float startY;
    private float xInterval;
    private float yInterval;

    private int i5 = 5;
    private int i10 = 10;
    private int i15 = 15;
    private int i20 = 20;
    private int i25 = 25;
    private int i30 = 30;
    private int i35 = 35;
    private int i40 = 40;

    public LineChart(Context context) {
        this(context, null);
    }

    public LineChart(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.DKGRAY);
        mPaint.setStrokeWidth(5);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(12);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private int getMeasuredSize(int measureSpec) {
        int finalSize = 0;
        int size = MeasureSpec.getSize(measureSpec);
        int mode = MeasureSpec.getMode(measureSpec);
        switch (mode) {
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                finalSize = (int) (size * 0.6);
                break;
        }
        return finalSize;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        startX = getPaddingLeft() + i30;
        startY = h - getPaddingBottom() - i30;

        //绘制X坐标
        float xendX = w - getPaddingRight() - i30;
        float xendY = h - getPaddingBottom() - i30;

        xPath.moveTo(startX, startY);
        xPath.lineTo(xendX, xendY);
        //绘制X坐标箭头
        xPath.moveTo(w - getPaddingRight() - i40, h - getPaddingBottom() - i35);//上方箭头
        xPath.lineTo(xendX, xendY);
        xPath.moveTo(w - getPaddingRight() - i40, h - getPaddingBottom() - i25);//下方箭头
        xPath.lineTo(xendX, xendY);
        //绘制X刻度
        float xSize = xendX - startX - getPaddingRight() - 50;
        xInterval = xSize / hours.length;
        for (int i = 1; i <= hours.length; i++) {
            float intervalX = startX + xInterval * i;
            float intervalY = startY - i10;
            xPath.moveTo(intervalX, startY);
            xPath.lineTo(intervalX, intervalY);
        }

        //绘制Y坐标
        float yendX = getPaddingLeft() + i30;
        float yendY = getPaddingTop() + i30;

        yPath.moveTo(startX, startY);
        yPath.lineTo(getPaddingLeft() + i30, getPaddingTop() + i30);
        //绘制Y坐标箭头
        yPath.moveTo(getPaddingLeft() + i25, getPaddingTop() + i40);//左边箭头
        yPath.lineTo(yendX, yendY);
        yPath.moveTo(getPaddingLeft() + i35, getPaddingTop() + i40);//右边箭头
        yPath.lineTo(yendX, yendY);
        //绘制Y刻度
        yInterval = (startY - yendY - getPaddingTop() - 50) / 21;
        for (int i = 1; i <= 21; i++) {
            float intervalX = startX + i10;
            float intervalY = startY - yInterval * i;
            yPath.moveTo(startX, intervalY);
            yPath.lineTo(intervalX, intervalY);
        }
        //绘制曲线，获取各个点坐标，连接，并在各个点画圆点
        int startT = 10, endT = 30;//温度范围10度到30度
        points.clear();
        for (int i = 1; i <= hours.length; i++) {
            float intervalX = startX + xInterval * i;
            float intervalY = startY - yInterval * (temperatures[i - 1] - 9);
            if (i == 1) {
                linePath.moveTo(intervalX, intervalY);
            } else {
                linePath.lineTo(intervalX, intervalY);
            }
            PointF point = new PointF(intervalX, intervalY);
            points.add(point);
        }
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.drawPath(xPath, mPaint);
        canvas.drawPath(yPath, mPaint);
        canvas.drawPath(linePath, mPaint);
        for (PointF point : points) {
            canvas.drawCircle(point.x, point.y, 4, mPaint);
        }
        //画出X坐标文字
        mPaint.setStrokeWidth(2);
        Paint.FontMetrics metrics = mPaint.getFontMetrics();
        float h = metrics.descent - metrics.ascent;
        for (int i = 0; i < hours.length; i++) {
            String s = String.valueOf(hours[i]);
            float fontSize = mPaint.measureText(s);
            float x = (startX+xInterval * (i+1))-fontSize/2;
            float y = startY+5+h;
            canvas.drawText(s,x,y,mPaint);
        }
        //画出Y坐标文字
        for (int i = 0; i < 21; i++) {
            String s = String.valueOf(10+i);
            float fontSize = mPaint.measureText(s);
            float x = startX-5-fontSize;
            float y = (startY-yInterval * (i+1))+h/3;
            canvas.drawText(s,x,y,mPaint);
        }
        canvas.restore();
    }
}
