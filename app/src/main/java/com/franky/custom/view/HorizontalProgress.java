package com.franky.custom.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.franky.custom.R;
import com.franky.custom.utils.UI;

/**
 * 自定义水平进度条
 */
public class HorizontalProgress extends ProgressBar {

    private Context mContex;

    private int reachbar_color = 0xff00ff00;
    private int unReachBar_color = 0xffff0000;
    private int des_color = 0xff0000ff;
    private int des_margin = UI.dp2px(getContext(), 10);
    private int des_size = UI.dp2sp(getContext(), 12);
    private int reachBar_height = UI.dp2px(getContext(), 4);
    private int unReachBar_height = UI.dp2px(getContext(), 2);

    private int realWidth;

    private Paint mPaint;


    public HorizontalProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
        initPaint();
    }


    private void initView(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.HorizontalProgress);

        reachbar_color = ta.getColor(R.styleable.HorizontalProgress_reachBar_color, reachbar_color);
        unReachBar_color = ta.getColor(R.styleable.HorizontalProgress_unReachBar_color, unReachBar_color);
        des_color = ta.getColor(R.styleable.HorizontalProgress_des_color, des_color);
        des_margin = (int) ta.getDimension(R.styleable.HorizontalProgress_des_margin, des_margin);
        des_size = (int) ta.getDimension(R.styleable.HorizontalProgress_des_size, des_size);
        reachBar_height = (int) ta.getDimension(R.styleable.HorizontalProgress_reachBar_height, reachBar_height);
        unReachBar_height = (int) ta.getDimension(R.styleable.HorizontalProgress_unReachBar_height, unReachBar_height);

        ta.recycle();
    }

    private void initPaint() {
        mPaint = new Paint();
        //设置绘制进度文本的大小
        mPaint.setTextSize(des_size);
    }


    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = MeasureSpec.getSize(widthMeasureSpec);//由于进度条的长度必须有用户指定，所以不必进行测量
        //只测量高度
        int height = measureHeight(heightMeasureSpec);

        setMeasuredDimension(width, height);

        //真正实际绘制的宽度
        realWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();

    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {

        canvas.save();
        canvas.translate(getPaddingLeft(), getHeight() / 2);
        boolean nodrawUnreachBar = false;//是否绘制unreachbar
        int textWidth = (int) mPaint.measureText(getProgress() + "%");
        //绘制reach_bar
        float radio = getProgress() * 1.0f / getMax();
        float progressX = radio * realWidth;

        if (progressX + textWidth > realWidth) {
            nodrawUnreachBar = true;
            progressX = realWidth - des_margin;
        }

        float endX = progressX - des_margin / 2;
        if (endX > 0) {
            mPaint.setStrokeWidth(reachBar_height);
            mPaint.setColor(reachbar_color);
            canvas.drawLine(0, 0, endX, 0, mPaint);
        }

        //绘制文本

        mPaint.setColor(des_color);
        int y = (int) (-(mPaint.descent() + mPaint.ascent()) / 2);
        canvas.drawText(getProgress() + "%", progressX, y, mPaint);
        //绘制unreach_bar

        if (!nodrawUnreachBar) {
            float start = progressX + des_margin / 2 + textWidth;
            mPaint.setColor(unReachBar_color);
            mPaint.setStrokeWidth(unReachBar_height);
            canvas.drawLine(start, 0, realWidth, 0, mPaint);
        }
        canvas.restore();

    }

    private int measureHeight(int heightMeasureSpec) {

        int result = 0;
        int mod = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (mod == MeasureSpec.EXACTLY) {
            result = height;
        } else {
            //获取文本的真实高度
            int textHeight = (int) (mPaint.descent() - mPaint.ascent());
            result = Math.max(Math.max(reachBar_height, unReachBar_height), Math.abs(textHeight)) + getPaddingBottom() + getPaddingTop();
            if (mod == MeasureSpec.AT_MOST) {
                result = Math.min(result, height);
            }
        }
        return result;
    }


}

