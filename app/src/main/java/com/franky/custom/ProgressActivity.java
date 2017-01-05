package com.franky.custom;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.franky.custom.view.HorizontalProgress;

/**
 * Created by Administrator on 2016/7/11.
 */
public class ProgressActivity extends AppCompatActivity {

    HorizontalProgress hp;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            int progress = hp.getProgress();
            if (progress < 100) {
                hp.setProgress(++progress);
                sendEmptyMessageDelayed(1, 100);
            } else {
                mHandler.removeMessages(1);
            }
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_activity);
        hp = (HorizontalProgress) findViewById(R.id.hp_progress);
        mHandler.sendEmptyMessage(1);

    }
}
