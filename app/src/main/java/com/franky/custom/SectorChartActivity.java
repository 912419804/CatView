package com.franky.custom;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.franky.custom.base.BaseActivity;
import com.franky.custom.bean.Pie;
import com.franky.custom.view.SectorChart;

import butterknife.BindView;

/**
 * 扇形图表
 */

public class SectorChartActivity extends BaseActivity {

    @BindView(R.id.cc_chart)
    SectorChart mSectorChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sectorchart);
    }

    @Override
    protected void initView() {
        super.initView();
        drawChart();
    }

    private void drawChart() {
        Pie pie1 = new Pie(50);
        Pie pie2 = new Pie(50);
        Pie pie3 = new Pie(50);
        Pie pie4 = new Pie(50);
        Pie pie5 = new Pie(50);
        Pie[] pies = {pie1, pie2, pie3, pie4, pie5};
        mSectorChart.setData(pies);
    }
}
