package com.franky.custom;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.franky.custom.base.BaseActivity;

import butterknife.BindView;

/**
 * 自定义View首页
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.bt_bezierCurve)
    Button bt_bezierCurve;
    @BindView(R.id.bt_canvasScale)
    Button bt_canvasScale;
    @BindView(R.id.bt_circleProgress)
    Button bt_circleProgress;
    @BindView(R.id.bt_font)
    Button bt_font;
    @BindView(R.id.bt_horizontalProgress)
    Button bt_horizontalProgress;
    @BindView(R.id.bt_lineChart)
    Button bt_lineChart;
    @BindView(R.id.bt_radarChart)
    Button bt_radarChart;
    @BindView(R.id.bt_sectorChart)
    Button bt_sectorChart;
    @BindView(R.id.bt_watch)
    Button bt_watch;
    @BindView(R.id.bt_wrappingPaper)
    Button bt_wrappingPaper;
    @BindView(R.id.bt_github)
    Button bt_github;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        super.initView();
        setMoreOnclickListener(bt_bezierCurve, bt_canvasScale, bt_circleProgress
                , bt_font, bt_horizontalProgress, bt_lineChart, bt_radarChart
                , bt_sectorChart, bt_watch, bt_wrappingPaper,bt_github);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.bt_bezierCurve:
                startActivity(BezierCurveActivity.class);
                break;
            case R.id.bt_canvasScale:
                startActivity(CanvasScaleActivity.class);
                break;
            case R.id.bt_circleProgress:
                startActivity(CircleProgressActivity.class);
                break;
            case R.id.bt_github:
                startActivity(GitHubActivity.class);
                break;
            case R.id.bt_font:
                startActivity(FontActivity.class);
                break;
            case R.id.bt_horizontalProgress:
                startActivity(HorizontalProgressActivity.class);
                break;
            case R.id.bt_lineChart:
                startActivity(LineChartActivity.class);
                break;
            case R.id.bt_radarChart:
                startActivity(RadarChartActivity.class);
                break;
            case R.id.bt_sectorChart:
                startActivity(SectorChartActivity.class);
                break;
            case R.id.bt_watch:
                startActivity(WatchActivity.class);
                break;
            case R.id.bt_wrappingPaper:
                startActivity(WrappingPaperActivity.class);
                break;
        }
    }
}