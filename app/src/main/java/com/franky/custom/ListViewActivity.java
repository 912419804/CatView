package com.franky.custom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.franky.custom.adapter.ItemClickAdapter;

import java.util.ArrayList;

/**
 * 可选择的listview
 */
public class ListViewActivity extends AppCompatActivity{

    private ListView lv_list;
    private ItemClickAdapter adapter;
    private ArrayList<String> data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_activity);
        initView();
    }

    private void initView() {
        lv_list = (ListView) findViewById(R.id.lv_list);
        data = new ArrayList<>();
        for (int i=0;i<30;i++){
            data.add("条目"+i);
        }
        adapter = new ItemClickAdapter(this,data);
        lv_list.setAdapter(adapter);
        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.updateItemState(view,position);
            }
        });

    }
}
