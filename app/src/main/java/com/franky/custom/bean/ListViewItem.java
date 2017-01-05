package com.franky.custom.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/11.
 */
public class ListViewItem implements Serializable {

    private int position;
    private boolean isSelect;

    public ListViewItem(int position, boolean isSelect) {
        this.position = position;
        this.isSelect = isSelect;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

}
