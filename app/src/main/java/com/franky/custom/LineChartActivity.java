package com.franky.custom;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.franky.custom.base.BaseActivity;

/**
 * Created by Administrator on 2017/2/3.
 * 自定义折线图效果
 */

public class LineChartActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linechart);
    }
}
