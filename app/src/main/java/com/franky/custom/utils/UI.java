package com.franky.custom.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by Administrator on 2016/12/31.
 */

public class UI {

    public static int dp2px(Context context, int i) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, i, context.getResources().getDisplayMetrics());
    }

    public static int dp2sp(Context context, int i) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, i, context.getResources().getDisplayMetrics());
    }

}
