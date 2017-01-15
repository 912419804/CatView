package com.franky.custom.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/13.
 * 封装每天的属性
 */

public class Day implements Serializable{

    /**年**/
    public int year;
    /**月**/
    public int month;
    /**日**/
    public int date;
    /**周几**/
    public int week;
    /**贡献次数**/
    public int contribution = 0;
    /**默认颜色**/
    public int colour = 0xFFEEEEEE;

    public float startX;
    public float startY;
    public float endX;
    public float endY;


    @Override
    public String toString() {
        return ""+year+"年"+month+"月"+date+"日周"+week+","+contribution+"次";
    }
}
