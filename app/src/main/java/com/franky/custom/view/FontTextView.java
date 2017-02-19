package com.franky.custom.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.franky.custom.R;


/**
 * Created by Administrator on 2017/1/20.
 * 可以自定义字体的TextView
 */

public class FontTextView extends TextView {

    /**
     * 默认字体
     */
    private static final String DEFUALT_FONT = "FZLanTingHeiS-L-GB-Regular.TTF";

    public FontTextView(Context context) {
        this(context, null);
    }

    public FontTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FontTextView, defStyleAttr, 0);
        String font = ta.getString(R.styleable.FontTextView_font);
        if (TextUtils.isEmpty(font)) {
            font = DEFUALT_FONT;
        }
        Typeface fontFace = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/" + font);
        // 字体文件必须是true type font的格式(ttf)；
        // 当使用外部字体却又发现字体没有变化的时候(以 Droid Sans代替)，通常是因为
        // 这个字体android没有支持,而非你的程序发生了错误
        setTypeface(fontFace);
        ta.recycle();
    }

}
