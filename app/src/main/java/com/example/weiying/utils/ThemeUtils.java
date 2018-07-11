package com.example.weiying.utils;

import android.content.Context;
import android.content.res.TypedArray;

/**
 * author:Created by WangZhiQiang on 2018/7/10.
 */
public class ThemeUtils {
    public static int getThemeColor(Context context, int attrRes) {
        TypedArray typedArray = context.obtainStyledAttributes(new int[]{attrRes});
        int color = typedArray.getColor(0, 0xffffff);
        typedArray.recycle();
        return color;
    }
}
