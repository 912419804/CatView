package com.franky.custom.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/31.
 * 扇形饼图数据
 */

public class Pie implements Serializable {

    public float num;
    public float angle = 0;

    public Pie(float num) {
        this.num = num;
    }
}
