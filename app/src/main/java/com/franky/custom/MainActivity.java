package com.franky.custom;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.franky.custom.view.CircleProgressBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CircleProgressBar cpb;
    private int progress = 0;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == Integer.MAX_VALUE) {
                progress++;
                if (progress >= 100) {
                    progress = 100;
                    cpb.setProgress(progress);
                    removeCallbacksAndMessages(null);
                } else {
                    cpb.setProgress(progress);
                    sendEmptyMessageDelayed(Integer.MAX_VALUE, 200);
                }
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cpb = (CircleProgressBar) findViewById(R.id.cc_chart);
        mHandler.sendEmptyMessageDelayed(Integer.MAX_VALUE, 200);
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
