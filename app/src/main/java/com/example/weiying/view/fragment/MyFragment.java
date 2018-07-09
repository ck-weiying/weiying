package com.example.weiying.view.fragment;

import android.view.View;

import com.example.weiying.R;
import com.example.weiying.presenter.BasePresenter;

/**
 * 我的页面
 */
public class MyFragment extends BaseFragment {
    @Override
    void initView(View view) {
        
    }

    @Override
    void initData() {

    }

    @Override
    BasePresenter setFragments() {
        return null;
    }

    @Override
    int setChildContenView() {
        return R.layout.activity_my;
    }
}
