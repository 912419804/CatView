package com.franky.custom;

import android.os.Bundle;

import com.franky.custom.base.BaseActivity;
import com.franky.custom.view.GitHubContributionView;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/2/3.
 * github提交表
 */

public class GitHubActivity extends BaseActivity {

    @BindView(R.id.cc_chart)
    GitHubContributionView github;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github);
    }

    @Override
    protected void initView() {
        super.initView();
        github.setData(2016, 12, 9, 2);
        github.setData(2016, 11, 9, 1);
        github.setData(2016, 10, 5, 10);
        github.setData(2016, 8, 9, 3);
        github.setData(2016, 4, 20, 2);
        github.setData(2016, 12, 13, 3);
        github.setData(2016, 12, 14, 3);
        github.setData(2016, 2, 15, 4);
    }
}
