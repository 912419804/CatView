package com.franky.custom.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/1/10.
 * 锯齿边,半圆边包装纸
 */

public class WrappingPaper extends View {

    private Paint mPaint = new Paint();
    private int mWidth;
    private int mHeight;
    private float triangleNum = 12.0f;//三角形数量

    public WrappingPaper(Context context) {
        this(context, null);
    }

    public WrappingPaper(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WrappingPaper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
//        drawTriangleSide(canvas);
        drawCircleSide(canvas);
        canvas.restore();
    }

    /**
     * 画半圆边
     * @param canvas
     */
    private void drawCircleSide(Canvas canvas) {
        canvas.translate(mWidth / 2, mHeight / 2);
        Rect rect = new Rect(-150, -60, 150, 60);
        canvas.drawRect(rect, mPaint);
        int rectHeight = rect.height();//贴纸高度
        float radius = rectHeight / triangleNum/2;//小圆形的半径
        for (int i = 0;i<triangleNum;i++){
            if (i !=0){
                canvas.drawCircle(rect.left,rect.top+2*radius*i+radius,radius,mPaint);
                canvas.drawCircle(rect.right,rect.top+2*radius*i+radius,radius,mPaint);
            }else {
                canvas.drawCircle(rect.left,rect.top+radius,radius,mPaint);
                canvas.drawCircle(rect.right,rect.top+radius,radius,mPaint);
            }
        }

    }

    /**
     * 画三角形边
     * @param canvas
     */
    private void drawTriangleSide(Canvas canvas) {
        canvas.translate(mWidth / 2, mHeight / 2);
        Rect rect = new Rect(-150, -60, 150, 60);
        canvas.drawRect(rect, mPaint);
        int rectHeight = rect.height();//贴纸高度
        float interval = rectHeight / triangleNum;//小三角形的靠近贴纸的边长
        Path path = new Path();
        path.moveTo(rect.left, rect.top);
        for (int i = 0; i < triangleNum; i++) {
            path.lineTo(rect.left, rect.top + interval * i);
            path.lineTo(rect.left - 5, rect.top + interval * i + interval / 2);
        }
        path.lineTo(rect.left, rect.bottom);
        path.close();
        canvas.drawPath(path, mPaint);
        path.reset();
        path.moveTo(rect.right, rect.top);
        for (int i = 0; i < triangleNum; i++) {
            path.lineTo(rect.right, rect.top + interval * i);
            path.lineTo(rect.right + 5, rect.top + interval * i + interval / 2);
        }
        path.lineTo(rect.right, rect.bottom);
        path.close();
        canvas.drawPath(path, mPaint);
    }
}
