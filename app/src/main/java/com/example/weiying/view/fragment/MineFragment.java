package com.example.weiying.view.fragment;

import android.view.View;

import com.example.weiying.R;
import com.example.weiying.presenter.MinePresenter;
import com.example.weiying.view.interfaces.IMineView;

/**
 * 我的
 * author:Created by WangZhiQiang on 2018/7/9.
 */
public class MineFragment extends BaseFragment<MinePresenter> implements IMineView {

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
    MinePresenter initPresenter() {
        return new MinePresenter();
    }

    @Override
    int setChildContentView() {
        return R.layout.fragment_mine;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPresenter().detachView();
    }
}
