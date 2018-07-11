package com.example.weiying.view.fragment;

import android.view.View;

import com.example.weiying.R;
import com.example.weiying.presenter.FeaturesPresenter;
import com.example.weiying.view.interfaces.IFeaturesView;

/**
 * 专题
 * author:Created by WangZhiQiang on 2018/7/9.
 */
public class FeaturesFragment extends BaseFragment<FeaturesPresenter> implements IFeaturesView {

    @Override
    void initView(View view) {

    }

    @Override
    void initData() {

    }

    @Override
    public void onSuccess(Object success) {

    }

    @Override
    FeaturesPresenter initPresenter() {
        return new FeaturesPresenter();
    }

    @Override
    int setChildContentView() {
        return R.layout.fragment_features;
    }
}
