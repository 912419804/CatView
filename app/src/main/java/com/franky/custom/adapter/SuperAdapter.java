package com.franky.custom.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/7/11.
 */
public abstract class SuperAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected List<T> mList;
    protected int mItemId;
    protected LayoutInflater mLayoutInflater;

    public SuperAdapter(Context context, List<T> list, int itemId) {
        mContext = context;
        mList = list;
        mItemId = itemId;
        mLayoutInflater = ((Activity) mContext).getLayoutInflater();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(mItemId, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        bindData(holder, position, mList.get(position));
        return convertView;
    }

    abstract void bindData(ViewHolder holder, int position, T t);


    public class ViewHolder {

        private View convertView;
        private SparseArray<View> views;

        public ViewHolder(View convertView) {
            this.convertView = convertView;
            views = new SparseArray<>();
        }


        protected <E extends View> E findView(int id) {
            View view = views.get(id);
            if (view == null) {
                view = convertView.findViewById(id);
                views.put(id, view);
            }
            return (E) view;
        }

        protected void setText(int id, String text) {
            TextView tv = findView(id);
            tv.setText(text);
        }

        protected void setBackGround(int viewId,int colorId){
            findView(viewId).setBackgroundColor(mContext.getResources().getColor(colorId));
        }
    }
}