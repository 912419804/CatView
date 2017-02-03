package com.franky.custom.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/13.
 * Activity 基类
 */

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        initView();
    }

    protected void initView() {

    }

    /**
     * 同时设置多个view的onClickListener方法
     *
     * @param views 需要设置的 view
     */
    protected void setMoreOnclickListener(View... views) {
        for (View view : views) {
            if (view != null) {
                view.setOnClickListener(this);
            }
        }
    }

    /**
     * 简化的startActivity方法
     *
     * @param cls Activity class
     */
    public void startActivity(Class<? extends Activity> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

    }
}
