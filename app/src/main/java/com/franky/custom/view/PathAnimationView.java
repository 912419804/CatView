package com.franky.custom.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by Administrator on 2017/2/6.
 * 利用PathMeasure和值动画进行路径动画绘制
 */

public class PathAnimationView extends View {

    private Paint mPaint;
    private Path mPath;
    private PathMeasure mPathMeasure;
    /**当前动画的数值**/
    private float mValue;
    /**path的总长度**/
    private float mPathLength;

    public PathAnimationView(Context context) {
        this(context, null);

    }

    public PathAnimationView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathAnimationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mPathMeasure = new PathMeasure();
        mPath = new Path();
        //初始化画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        //初始化圆
        mPath.addCircle(400, 400, 100, Path.Direction.CW);
        mPathMeasure.setPath(mPath, true);
        mPathLength = mPathMeasure.getLength();
        //利用值动画进行界面刷新
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setDuration(1000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.start();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPath.reset();
        // 硬件加速的BUG
        mPath.lineTo(0,0);
        float stop = mPathLength*mValue;
        float start = 0;
//        float start = (float) (stop - ((0.5 - Math.abs(mValue - 0.5)) * mPathLength));
        Log.d("路径",stop+"=="+start);
        mPathMeasure.getSegment(start,stop,mPath,true);
        canvas.drawPath(mPath,mPaint);
    }
}
