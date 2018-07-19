package com.example.weiying.view.fragment;

import android.view.View;

import com.example.weiying.R;
import com.example.weiying.presenter.LivePresenter;
import com.example.weiying.view.interfaces.ILiveView;

/**
 * author:Created by WangZhiQiang on 2018/7/17.
 */
public class LiveFragment extends BaseFragment<LivePresenter> implements ILiveView{

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
    LivePresenter initPresenter() {
        return new LivePresenter();
    }

    @Override
    int setChildContentView() {
        return R.layout.fragment_live;
    }
}
