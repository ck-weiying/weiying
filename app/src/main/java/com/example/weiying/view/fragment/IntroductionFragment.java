package com.example.weiying.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.weiying.R;
import com.example.weiying.model.bean.DetailsBean;
import com.example.weiying.presenter.BasePresenter;
import com.example.weiying.utils.TransAnim;
import com.example.weiying.view.adapter.IntroductionAdapter;

/**
 * 简介
 * author:Created by WangZhiQiang on 2018/7/16.
 */
public class IntroductionFragment extends BaseFragment implements View.OnClickListener {

    private RecyclerView introduction_rv;
    private TextView introduction_director_tv;
    private TextView introduction_starring_tv;
    private TextView introduction_tv;
    private TextView hide;

    private boolean flag = false;

    @Override
    void initView(View view) {
        introduction_rv = view.findViewById(R.id.introduction_rv);
        introduction_director_tv = view.findViewById(R.id.introduction_director_tv);
        introduction_starring_tv = view.findViewById(R.id.introduction_starring_tv);
        introduction_tv = view.findViewById(R.id.introduction_tv);
        hide = view.findViewById(R.id.hide);
        hide.setOnClickListener(this);

        DetailsBean.RetBean ret= (DetailsBean.RetBean) getArguments().getSerializable("ret");

        introduction_director_tv.setText("导演:" + ret.getDirector());
        introduction_starring_tv.setText("主演:" + ret.getActors());
        introduction_tv.setText("简介:" + ret.getDescription());

        introduction_rv.setNestedScrollingEnabled(false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        introduction_rv.setLayoutManager(gridLayoutManager);
        IntroductionAdapter historyAdapter = new IntroductionAdapter(getActivity(), ret.getList().get(0).getChildList());
        introduction_rv.setAdapter(historyAdapter);

        TransAnim.toFir(flag, getActivity(), 300, introduction_rv);
    }

    @Override
    public void onClick(View v) {
        TransAnim.toFir(flag, getActivity(), 300, introduction_rv);
        if (flag) {
            hide.setText("展开");
        } else {
            hide.setText("收起");
        }
        flag = !flag;
    }

    @Override
    void initData() {

    }

    @Override
    public void onSuccess(Object success) { }

    @Override
    BasePresenter initPresenter() {
        return null;
    }

    @Override
    int setChildContentView() {
        return R.layout.fragment_details_introduction;
    }

    public static IntroductionFragment getInstances(DetailsBean.RetBean ret) {
        IntroductionFragment fragmentTabs = new IntroductionFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("ret", ret);
        fragmentTabs.setArguments(bundle);
        return fragmentTabs;
    }
}
