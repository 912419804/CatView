package com.franky.custom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.franky.custom.view.GitHubContributionView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GitHubContributionView github = (GitHubContributionView) findViewById(R.id.cc_chart);
        github.setData(2016,12,9,2);
        github.setData(2016,11,9,1);
        github.setData(2016,10,5,10);
        github.setData(2016,8,9,3);
        github.setData(2016,4,20,2);
        github.setData(2016,12,13,3);
        github.setData(2016,12,14,3);
        github.setData(2016,2,15,4);
    }
}
