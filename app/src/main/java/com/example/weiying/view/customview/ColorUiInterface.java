package com.example.weiying.view.customview;

import android.content.res.Resources;
import android.view.View;

/**
 * 换肤接口
 * author:Created by WangZhiQiang on 2018/7/10.
 */
public interface ColorUiInterface {
    View getView();

    void setTheme(Resources.Theme themeId);
}
