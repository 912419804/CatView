package com.franky.custom;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.franky.custom.base.BaseActivity;
import com.franky.custom.view.HorizontalProgress;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/7/11.
 * 自定义水平进度条
 */
public class HorizontalProgressActivity extends BaseActivity {

    @BindView(R.id.hp_progress)
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
        setContentView(R.layout.activity_horizontalprogress);

    }

    @Override
    protected void initView() {
        super.initView();
        hp = (HorizontalProgress) findViewById(R.id.hp_progress);
        mHandler.sendEmptyMessage(1);
    }
}
