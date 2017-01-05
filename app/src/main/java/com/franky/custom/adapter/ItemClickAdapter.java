package com.franky.custom.adapter;

import android.content.Context;
import android.view.View;

import com.franky.custom.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemClickAdapter extends SuperAdapter<String> {

    private ArrayList<HashMap<Integer,Boolean>> mHolders;

    private int lastPosition = -1;

    public ItemClickAdapter(Context context, List<String> list) {
        super(context, list, R.layout.listview_item);
        mHolders = new ArrayList<>();
    }

    @Override
    protected void bindData(ViewHolder holder, final int position, String s) {
        holder.setText(R.id.tv_test, s);
        if (mHolders.get(position)==null){
            mHolders.add(new HashMap<Integer, Boolean>(position));
        }
        if (lastPosition ==-1){
            holder.setBackGround(R.id.ll_test, android.R.color.holo_green_light);
        }
        if (lastPosition!=-1 && lastPosition==position){
            holder.setBackGround(R.id.ll_test, android.R.color.holo_orange_light);
        }
    }


    public void updateItemState(View view, int position) {
        lastPosition = position;
        mHolders.get(position).put(position,true);
    }
}
