package com.franky.custom.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2017/1/1.
 * 雷达图
 */

public class RadarChart extends View {

    private int centerX;//中心点X坐标
    private int centerY;//中心点Y坐标
    private Paint mPaint;

    private Path mPath;
    private float radius;//最外层的外接圆半径
    private float currentRadius;
    private String[] courses = new String[]{"数学", "语文", "地理", "历史", "政治", "化学"};//科目
    private Integer[] scores = new Integer[]{100, 50, 20, 70, 90, 80};//分数
    private int count = courses.length;//雷达图的层次及每个多边形的边数
    private double angle = 2 * Math.PI / count;//圆周等分后的弧度

    public RadarChart(Context context) {
        this(context, null);
    }

    public RadarChart(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadarChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.DKGRAY);
        mPaint.setStrokeWidth(2);
        mPath = new Path();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        centerX = w / 2;
        centerY = h / 2;
        radius = Math.min(w, h) * 0.4f;
        currentRadius = radius / (count - 1);
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//
//        mPath.moveTo(0, centerY);
//        mPath.lineTo(2 * centerX, centerY);
//        mPath.moveTo(centerX, 0);
//        mPath.lineTo(centerX, 2 * centerY);
//        canvas.drawPath(mPath, mPaint);

//        1.先画出嵌套的正多边形
        for (int i = 1; i < count; i++) {//循环画每一圈,原点不用画
            mPath.reset();
            for (int j = 0; j < count; j++) {//循环画单个圈上的每条线
                if (j == 0) {
                    mPath.moveTo(centerX + currentRadius * i, centerY);//
                } else {//利用三角函数公式算出点的坐标
                    float x = (float) (centerX + Math.cos(j * angle) * currentRadius * i);
                    float y = (float) (centerY + Math.sin(j * angle) * currentRadius * i);
                    mPath.lineTo(x, y);
                }
            }
            mPath.close();
            canvas.drawPath(mPath, mPaint);
        }
//      2.再将正多边形的各个对角连接起来
        for (int i = 0; i < count; i++) {
            mPath.reset();
            mPath.moveTo(centerX, centerY);
            float x = (float) (centerX + (Math.cos(i * angle) * radius));
            float y = (float) (centerY + Math.sin(i * angle) * radius);
            mPath.lineTo(x, y);
            canvas.drawPath(mPath, mPaint);
        }

//        3.画出各个角的文字说明，需要根据所在的象限来进行文字距离的计算和绘制
        mPaint.setTextSize(12);
        Paint.FontMetrics metrics = mPaint.getFontMetrics();
        float fontHeight = metrics.descent - metrics.ascent;
        Log.e("字体",fontHeight+"");
        float margin = 5;
        for (int i = 0; i < count; i++) {
            double sweepAngle = i * angle;
            float x = (float) (centerX + (Math.cos(sweepAngle) * radius));
            float y = (float) (centerY + Math.sin(sweepAngle) * radius);
            if (sweepAngle >= 0 && sweepAngle < Math.PI / 2) {//右下象限
                canvas.drawText(courses[i], x + margin, y+fontHeight/2, mPaint);
            } else if (sweepAngle >= Math.PI / 2 && sweepAngle <= Math.PI) {//左下象限
                float textLength = mPaint.measureText(courses[i]);
                canvas.drawText(courses[i], x - margin - textLength, y+fontHeight/2, mPaint);
            } else if (sweepAngle > Math.PI && sweepAngle < Math.PI / 2 * 3) {//左上象限
                float textLength = mPaint.measureText(courses[i]);
                canvas.drawText(courses[i], x - margin - textLength, y-fontHeight/2, mPaint);
            } else if (sweepAngle >= Math.PI / 2 * 3 && sweepAngle < 2 * Math.PI) {//右上象限
                canvas.drawText(courses[i], x + margin, y-fontHeight/2, mPaint);
            }
        }
//        4.根据分数计算百分比画出线段，并连接及填充颜色
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setARGB(55, 99, 22, 44);
        for (int i = 0; i < count; i++) {
            float percent = scores[i] / 100f;
            float x = (float) (centerX + Math.cos(i * angle) * radius * percent);
            float y = (float) (centerY + Math.sin(i * angle) * radius * percent);
            canvas.drawCircle(x, y, 4, mPaint);
            if (i == 0) {
                mPath.moveTo(x, y);
            } else {
                mPath.lineTo(x, y);
            }
        }
        canvas.drawPath(mPath, mPaint);
    }
}
