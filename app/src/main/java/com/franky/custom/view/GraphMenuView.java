package com.franky.custom.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/1/23.
 * 图形菜单
 */

public class GraphMenuView extends View {

    //三角形菜单外部画笔
    private Paint outTrianglePaint;
    //三角形菜单内部画笔
    private Paint innerTrianglePaint;

    public GraphMenuView(Context context) {
        this(context,null);
    }

    public GraphMenuView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public GraphMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        outTrianglePaint = new Paint();
        outTrianglePaint.setStyle(Paint.Style.STROKE);
        outTrianglePaint.setColor(Color.BLACK);
        outTrianglePaint.setAntiAlias(true);
        outTrianglePaint.setStrokeWidth(5);

        innerTrianglePaint = new Paint();
        innerTrianglePaint.setStyle(Paint.Style.FILL);
        innerTrianglePaint.setColor(Color.BLACK);
        outTrianglePaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(4487,54);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
