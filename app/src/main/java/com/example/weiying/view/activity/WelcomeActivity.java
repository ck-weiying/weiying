package com.example.weiying.view.activity;

import android.os.Bundle;

import com.example.weiying.R;
import com.example.weiying.presenter.BasePresenter;

public class WelcomeActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    void initView() {
        
    }

    @Override
    void initData() {

    }

    @Override
    BasePresenter setBasePresenter() {
        return null;
    }

    @Override
    int setChildContentView() {
        return R.layout.activity_welcome;
    }
}
