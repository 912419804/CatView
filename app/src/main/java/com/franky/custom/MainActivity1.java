package com.franky.custom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity1 extends AppCompatActivity implements View.OnClickListener {

    private Button bt_pro;
    private Button bt_lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        initView();
    }

    private void initView() {
        bt_pro = (Button) findViewById(R.id.bt_pro);
        bt_lv = (Button) findViewById(R.id.bt_lv);
        bt_pro.setOnClickListener(this);
        bt_lv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_pro:
                startActivity(new Intent(this,ProgressActivity.class));
                break;
            case R.id.bt_lv:
                startActivity(new Intent(this,ListViewActivity.class));
                break;
        }
    }
}
