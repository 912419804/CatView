package com.franky.custom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//    private void drawChart() {
//        Pie pie1 = new Pie(50);
//        Pie pie2 = new Pie(50);
//        Pie pie3 = new Pie(50);
//        Pie pie4 = new Pie(50);
//        Pie pie5 = new Pie(50);
//        Pie[] pies = {pie1, pie2, pie3,pie4,pie5};
//        CircularChart3 chart = (CircularChart3) findViewById(R.id.cc_chart);
//        chart.setData(pies);
//    }
    private void drawChart() {
    }

    private void initView() {
    }

    @Override
    public void onClick(View v) {

    }
}
