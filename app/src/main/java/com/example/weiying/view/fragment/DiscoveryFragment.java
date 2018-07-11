package com.example.weiying.view.fragment;

import android.view.View;

import com.example.weiying.R;
import com.example.weiying.presenter.DiscoveryPresenter;
import com.example.weiying.view.interfaces.IDiscoveryView;

/**
 * 发现
 * author:Created by WangZhiQiang on 2018/7/9.
 */
public class DiscoveryFragment extends BaseFragment<DiscoveryPresenter> implements IDiscoveryView {

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
    DiscoveryPresenter initPresenter() {
        return new DiscoveryPresenter();
    }

    @Override
    int setChildContentView() {
        return R.layout.fragment_discovery;
    }
}
