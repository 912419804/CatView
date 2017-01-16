package com.franky.custom.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.franky.custom.bean.DateFactory;
import com.franky.custom.bean.Day;
import com.franky.custom.utils.UI;

import java.util.List;

/**
 * Created by Administrator on 2017/1/13.
 * 仿GitHub的提交活跃表
 * 横屏使用
 */

public class GitHubContributionView extends View {

    private final static int DEFAULT_BOX_COLOUR = 0xFFEEEEEE;//灰色方格的默认颜色
    private final static int[] COLOUR_LEVEL = new int[]{0xFF1E6823, 0xFF44A340, 0xFF8CC665, 0xFFD6E685, DEFAULT_BOX_COLOUR};//提交次数颜色值
    private String[] weeks = new String[]{"Mon", "Wed", "Fri", "Sun"};//星期
    private String[] months = new String[]{"Jan", "Feb", "Mar", "Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};//月份
    private int padding = 24;//默认的padding
    private int boxSide = 8;//小方格的默认边长
    private int boxInterval = 2;//小方格间的默认间隔

    private int column = 0;//所有周的列数

    private List<Day> mDays;//一年中所有的天
    private Paint boxPaint;//方格画笔
    private Paint textPaint;//文字画笔
    private Paint infoPaint;//弹出框画笔

    private Paint.FontMetrics metrics;//测量文字

    private int width;
    private int height;

    private float downX;//按下的点的X坐标
    private float downY;//按下的点的Y坐标
    private Day clickDay;//按下所对应的天

    public GitHubContributionView(Context context) {
        this(context, null);
    }

    public GitHubContributionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GitHubContributionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void initView() {
        mDays = DateFactory.getDays(2016, 5);
        //方格画笔
        boxPaint = new Paint();
        boxPaint.setStyle(Paint.Style.FILL);
        boxPaint.setStrokeWidth(2);
        boxPaint.setColor(DEFAULT_BOX_COLOUR);
        boxPaint.setAntiAlias(true);
        //文字画笔
        textPaint = new Paint();
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.GRAY);
        textPaint.setTextSize(12);
        textPaint.setAntiAlias(true);
        //弹出的方格信息画笔
        infoPaint = new Paint();
        infoPaint.setStyle(Paint.Style.FILL);
        infoPaint.setColor(0xCC888888);
        infoPaint.setTextSize(12);
        infoPaint.setAntiAlias(true);
        //将默认值转换px
        padding = UI.dp2px(getContext(), padding);
        boxSide = UI.dp2px(getContext(), boxSide);

        metrics = textPaint.getFontMetrics();
//        boxInterval = UI.dp2px(getContext(), boxInterval);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w;
        height = h;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        column = 0;
        canvas.save();
        drawBox(canvas);
        drawWeek(canvas);
        drawTag(canvas);
        drawPopupInfo(canvas);
        canvas.restore();
    }

    /**
     * 画出1-12月方格小块
     *
     * @param canvas 画布
     */
    private void drawBox(Canvas canvas) {
        float startX, startY, endX, endY;
        int month = 1;
        for (int i = 0; i < mDays.size(); i++) {
            Day day = mDays.get(i);
            if (i == 0){
                //画1月的
                canvas.drawText(months[0],padding,padding-boxSide/2,textPaint);
            }
            if (day.week == 1 && i != 0) {
                column++;
                //如果列首的月份有变化，那么说明需要画月份
                if (day.month>month){
                    month = day.month;
                    canvas.drawText(months[month-1],padding+column*(boxSide+boxInterval),padding-boxSide/2,textPaint);
                }
            }
            //计算坐标点
            startX = padding + column * (boxSide + boxInterval);
            startY = padding + (day.week - 1) * (boxSide + boxInterval);
            endX = startX + boxSide;
            endY = startY + boxSide;
            //将该方格的坐标保存下来
            day.startX = startX;
            day.startY = startY;
            day.endX = endX;
            day.endY = endY;
            if (day.colour != DEFAULT_BOX_COLOUR){
                boxPaint.setColor(day.colour);
            }
            canvas.drawRect(startX, startY, endX, endY, boxPaint);
            boxPaint.setColor(DEFAULT_BOX_COLOUR);
        }

    }

    /**
     * 画左侧的星期
     * @param canvas
     */
    private void drawWeek(Canvas canvas) {
        //找出最长的字,左对齐开始画
        float textLength = 0;
        for (String week : weeks) {
            float tempLength = textPaint.measureText(week);
            if (textLength < tempLength) {
                textLength = tempLength;
            }
        }
        //依次画出星期
        canvas.drawText(weeks[0], padding - textLength - 2, padding + boxSide - metrics.descent, textPaint);
        canvas.drawText(weeks[1], padding - textLength - 2, padding + 3 * (boxSide + boxInterval) - metrics.descent, textPaint);
        canvas.drawText(weeks[2], padding - textLength - 2, padding + 5 * (boxSide + boxInterval) - metrics.descent, textPaint);
        canvas.drawText(weeks[3], padding - textLength - 2, padding + 7 * (boxSide + boxInterval) - metrics.descent, textPaint);
    }

    /**
     * 画出右下角的颜色深浅标志，因为是右对齐的所以需要从右往左画
     *
     * @param canvas 画布
     */
    private void drawTag(Canvas canvas) {
        float moreLength = textPaint.measureText("More");
        float lessLength = textPaint.measureText("Less");
        //画 More
        float moreX = padding + (column + 1) * (boxSide + boxInterval) - boxInterval - moreLength;
        float moreY = padding + 8 * (boxSide + boxInterval) + Math.abs(metrics.ascent);
        canvas.drawText("More", moreX, moreY, textPaint);
        //画深浅色块
        float interval = boxSide - 2;//文字和色块间的距离
        float leftX = moreX - interval - boxSide;
        float topY = moreY - boxSide;
        float rightX = moreX - interval;
        float bottomY = moreY;//色块的Y坐标是一样的
        for (int i = 0; i < COLOUR_LEVEL.length; i++) {
            boxPaint.setColor(COLOUR_LEVEL[i]);
            canvas.drawRect(leftX - i * (boxSide + boxInterval), topY, rightX - i * (boxSide + boxInterval), bottomY, boxPaint);
        }
        //画Less
        canvas.drawText("Less", leftX - 4 * (boxSide + boxInterval) - interval - lessLength, moreY, textPaint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取点击时候的坐标，用来判断点在哪天，并弹出·
        if (MotionEvent.ACTION_DOWN == event.getAction()) {
            downX = event.getX();
            downY = event.getY();
            findClickBox();
            return true;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 判断是否点击在方格内
     */
    private void findClickBox() {
        for (Day day : mDays) {
            //检测点击的坐标如果在方格内，则弹出信息提示
            if (downX >= day.startX && downX <= day.endX && downY >= day.startY && downY <= day.endY) {
                clickDay = day;
                break;
            }
        }
        refreshView();
    }

    /**
     * 点击弹出文字提示
     */
    private void refreshView() {
        invalidate();
    }

    private void drawPopupInfo(Canvas canvas) {
        if (clickDay != null) {
            //先根据方格来画出一个小三角形
            Path infoPath = new Path();
            infoPath.moveTo(clickDay.startX + boxSide / 2, clickDay.startY + boxSide / 2);
            infoPath.lineTo(clickDay.startX, clickDay.startY);
            infoPath.lineTo(clickDay.endX, clickDay.startY);
            canvas.drawPath(infoPath,infoPaint);
            //添加圆角矩形
            textPaint.setColor(Color.WHITE);
            String popupInfo = clickDay.toString();
            System.out.println(popupInfo);
            float infoHeight = metrics.descent - metrics.ascent;
            float infoLength = textPaint.measureText(popupInfo);
            Log.e("height",infoHeight+"");
            Log.e("length",infoLength+"");
            float leftX = (clickDay.startX + boxSide / 2 ) - (infoLength / 2 + boxSide);
            float topY = clickDay.startY-(infoHeight+2*boxSide);
            float rightX = (clickDay.startX + boxSide / 2 ) + (infoLength / 2 + boxSide);
            float bottomY = clickDay.startY;
            System.out.println(""+leftX+"/"+topY+"/"+rightX+"/"+bottomY);
            RectF rectF = new RectF(leftX, topY, rightX, bottomY);
            canvas.drawRoundRect(rectF,4,4,infoPaint);
            //绘制文字
            canvas.drawText(popupInfo,leftX+boxSide,topY+boxSide-metrics.ascent,textPaint);
            clickDay = null;//重新置空，保证点击方格外信息消失
            textPaint.setColor(Color.GRAY);
        }
    }

    /**
     * 设置某天的次数
     * @param year 年
     * @param month 月
     * @param day 日
     * @param contribution 次数
     */
    public void setData(int year,int month,int day,int contribution){
        //先找到是第几天，为了方便不做参数检测了
        for (Day d : mDays) {
            if (d.year == year && d.month == month && d.date == day){
                d.contribution = contribution;
                d.colour = getColour(contribution);
                break;
            }
        }
        refreshView();
    }

    private int getColour(int contribution){
        int colour = 0;
        if (contribution <= 0){
            colour = COLOUR_LEVEL[4];
        }
        if (contribution == 1){
            colour = COLOUR_LEVEL[3];
        }
        if (contribution == 2){
            colour = COLOUR_LEVEL[2];
        }
        if (contribution == 3){
            colour = COLOUR_LEVEL[1];
        }
        if (contribution >= 4){
            colour = COLOUR_LEVEL[0];
        }
        return colour;
    }

}
