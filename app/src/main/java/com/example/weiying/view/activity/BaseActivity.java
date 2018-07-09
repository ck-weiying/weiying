package com.example.weiying.view.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.weiying.presenter.BasePresenter;
import com.example.weiying.view.interfaces.IBaseView;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {
    public P presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //沉浸式
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        super.onCreate(savedInstanceState);
        setContentView(setChildContentView());
        initView();
        initDataBase();
        initData();
    }
    public P getPresenter() {
        return presenter;
    }

    private void initDataBase() {
        presenter = setBasePresenter();
        if (presenter != null) {
            presenter.attachView(this);
        } else {
            try {
                throw new Exception("兄弟 prenter 没有设置 请在您的Activity 创建 presenter！！！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    abstract void initView();
    abstract void initData();
    abstract P setBasePresenter();
    abstract int setChildContentView();
}