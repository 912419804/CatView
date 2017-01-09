package com.franky.custom.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Calendar;

/**
 * Created by Administrator on 2017/1/8.
 * 自定义圆形时钟
 */

public class WatchView extends View {

    /**
     * 表盘画笔
     **/
    private Paint mPaint = new Paint();
    /**
     * 小时刻度画笔
     **/
    private Paint hourPaint = new Paint();
    /**
     * 分刻度画笔
     **/
    private Paint secPaint = new Paint();
    /**
     * 数字画笔
     **/
    private Paint textPaint = new Paint();
    /**
     * 小时刻度的长度
     **/
    private float hourSize = 30;
    /**
     * 分刻度的长度
     **/
    private float secSize = (float) (hourSize * 0.8);

    private float centerX;
    private float centerY;
    /**
     * 大表盘的半径，计算得出
     **/
    private float bigRadius;
    /**
     * 小时刻度的半径，比表盘半径略小
     **/
    private float hourRadius;



    public WatchView(Context context) {
        this(context, null);
    }

    public WatchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WatchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        //表盘画笔
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);
        mPaint.setShadowLayer(10,0,0,Color.DKGRAY);
        //小时刻度画笔
        hourPaint.setStyle(Paint.Style.STROKE);
        hourPaint.setColor(Color.BLACK);
        hourPaint.setStrokeWidth(4);
        hourPaint.setStrokeCap(Paint.Cap.ROUND);
        hourPaint.setAntiAlias(true);
        //分刻度画笔
        secPaint.setStyle(Paint.Style.STROKE);
        secPaint.setColor(Color.GRAY);
        secPaint.setStrokeWidth(2);
        secPaint.setStrokeCap(Paint.Cap.ROUND);
        secPaint.setAntiAlias(true);
        //小时数字画笔
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.BLACK);
        textPaint.setStrokeWidth(1);
        textPaint.setStrokeCap(Paint.Cap.ROUND);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(18);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        centerX = w / 2;
        centerY = h / 2;
        bigRadius = (float) (w / 2 * 0.9);
        hourRadius = (float) (bigRadius * 0.95);
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        hourPaint.setColor(Color.BLACK);
        hourPaint.setStrokeWidth(4);
        canvas.save();
        //移动到视图中心
        canvas.translate(centerX, centerY);
        canvas.save();
        //画表盘
        canvas.drawCircle(0, 0, bigRadius, mPaint);
        //画刻度,每个刻度旋转6度，从12点的位置画起
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;
        Log.e("font height",fontHeight+"");
        for (int i = 0; i < 60; i++) {
            if (i % 5 != 0) {
                canvas.drawLine(-hourRadius, 0, -hourRadius + secSize, 0, secPaint);
            } else if (i % 5 == 0) {//小时刻度，
                canvas.drawLine(-hourRadius, 0, -hourRadius + hourSize, 0, hourPaint);
                String hour = (i == 0 ? 12 : i / 5) + "";
                Rect rect = new Rect();
                textPaint.getTextBounds(hour, 0, hour.length(), rect);
//                Log.e("bottom",rect.bottom+"");
//                Log.e("top",rect.top+"");
//                Log.e("left",rect.left+"");
//                Log.e("right",rect.right+"");
//                Log.e("height",rect.height()+"");
//                Log.e("width",rect.width()+"");
                int textHeight = rect.height();
                canvas.save();
                canvas.translate(0, -hourRadius + hourSize + 15 + textHeight / 2);
                canvas.rotate(-6 * i);
                canvas.drawText(hour, -(rect.right + rect.left) / 2, -(rect.bottom+rect.top)/2, textPaint);
                canvas.restore();
            }
            canvas.rotate(6);
        }
        canvas.restore();//重新将坐标原点恢复到画布中心
        canvas.save();
        //画时分秒的指针
        Calendar mCalendar = Calendar.getInstance();
        int hour = mCalendar.get(Calendar.HOUR);
        int minute = mCalendar.get(Calendar.MINUTE);
        int second = mCalendar.get(Calendar.SECOND);
        float hourAngel = hour / 12.0f * 360;
        float minuteAngel = minute / 60.f * 360;
        float secondAngel = second /60.f  * 360;
        Log.w("secondAngel",secondAngel+"");
        //绘制时针
        canvas.rotate(hourAngel);
        hourPaint.setStrokeWidth(12);
        canvas.drawLine(0,20,0,-100,hourPaint);
        canvas.restore();
        //绘制分针
        canvas.save();
        canvas.rotate(minuteAngel);
        hourPaint.setStrokeWidth(10);
        canvas.drawLine(0,20,0,-120,hourPaint);
        canvas.restore();
        //绘制秒针
        canvas.save();
        canvas.rotate(secondAngel);
        hourPaint.setStrokeWidth(6);
        hourPaint.setColor(Color.RED);
        hourPaint.setStyle(Paint.Style.FILL);
        canvas.drawLine(0,20,0,-hourRadius,hourPaint);
        canvas.restore();
        //画中心的红色小圆盘
        canvas.save();
        canvas.drawCircle(0, 0, 10, hourPaint);
        canvas.restore();
        postInvalidateDelayed(1000);
    }
}
