package com.franky.custom.view;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/1/11.
 * 弧形进度条,不带刻度
 */

public class ArcProgressBar extends View{

    private Paint outPaint;//外部画笔
    private Paint innerPaint;//内部进度画笔

    public ArcProgressBar(Context context) {
        this(context,null);
    }

    public ArcProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ArcProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        outPaint = new Paint();
        innerPaint = new Paint();


    }
}
