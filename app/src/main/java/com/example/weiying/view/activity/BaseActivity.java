package com.example.weiying.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.example.weiying.R;
import com.example.weiying.presenter.BasePresenter;
import com.example.weiying.view.interfaces.IBaseView;

/**
 * author:Created by WangZhiQiang on 2018/7/5.
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {
    private P p;
    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (setChildContentView()== R.layout.activity_push_stream){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
        rootView = getLayoutInflater().from(this).inflate(setChildContentView(), null);
        setContentView(rootView);

        initView();

        p=initPresenter();
        if (p != null) {
            p.attachView(this);
        }else {
            try {
                throw new Exception("少年 prenter 没有设置 请在您的Activity 创建 presenter");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        initData();
    }
    public View getLayoutView() {
        return rootView;
    }

    public P getPresenter() {
        return p;
    }

    abstract void initView();
    abstract void initData();
    abstract P initPresenter();
    abstract int setChildContentView();
}

