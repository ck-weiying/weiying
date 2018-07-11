package com.example.weiying.view.fragment;

import android.view.View;

import com.example.weiying.R;
import com.example.weiying.presenter.FeaturedPresenter;
import com.example.weiying.view.interfaces.IFeaturedView;

/**
 * 精选
 * author:Created by WangZhiQiang on 2018/7/9.
 */
public class FeaturedFragment extends BaseFragment<FeaturedPresenter> implements IFeaturedView {

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
    FeaturedPresenter initPresenter() {
        return new FeaturedPresenter();
    }

    @Override
    int setChildContentView() {
        return R.layout.fragment_featured;
    }
}
