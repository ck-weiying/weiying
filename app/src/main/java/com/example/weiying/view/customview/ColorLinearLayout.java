package com.example.weiying.view.customview;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.example.weiying.utils.ViewAttributeUtil;
import com.example.weiying.view.interfaces.ColorUiInterface;

public class ColorLinearLayout extends LinearLayout implements ColorUiInterface {

    private int attr_background = -1;

    public ColorLinearLayout(Context context) {
        super(context);
    }

    public ColorLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.attr_background = ViewAttributeUtil.getBackgroundAttibute(attrs);
    }

    public ColorLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.attr_background = ViewAttributeUtil.getBackgroundAttibute(attrs);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void setTheme(Resources.Theme themeId) {
        if (attr_background != -1) {
            ViewAttributeUtil.applyBackgroundDrawable(this, themeId, attr_background);
        }
    }
}
