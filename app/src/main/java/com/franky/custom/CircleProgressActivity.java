package com.franky.custom;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.franky.custom.base.BaseActivity;
import com.franky.custom.view.CircleProgressBar;

/**
 * 带刻度圆形进度条
 */
public class CircleProgressActivity extends BaseActivity {

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
        setContentView(R.layout.activity_circleprogress);
        cpb = (CircleProgressBar) findViewById(R.id.cc_chart);
        mHandler.sendEmptyMessageDelayed(Integer.MAX_VALUE, 200);
    }
}
