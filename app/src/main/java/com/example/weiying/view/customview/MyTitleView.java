package com.example.weiying.view.customview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * author:Created by WangZhiQiang on 2018/7/6.
 */
public class MyTitleView extends RelativeLayout {
    private Context context;
    private TextView textView;

    public MyTitleView(Context context) {
        this(context,null);
    }

    public MyTitleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        initView(context);
    }

    private void initView(final Context context) {
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        textView = new TextView(context);
        textView.setText("标题");
        textView.setTextSize(20);
        textView.setTextColor(Color.WHITE);
        addView(textView,params);
    }

    public TextView getTitle(){
        return textView;
    }
}

