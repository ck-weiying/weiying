package com.example.weiying.view.activity;

import com.example.weiying.R;
import com.example.weiying.model.bean.FeaturedBean;
import com.example.weiying.presenter.SearchPresenter;
import com.example.weiying.view.interfaces.ISearchView;

import java.util.ArrayList;

public class SearchActivity extends BaseActivity<SearchPresenter> implements ISearchView {


    @Override
    void initView() {

    }

    @Override
    void initData() {
        ArrayList<FeaturedBean.RetBean.HotSearchListBean> hotSearchList = (ArrayList<FeaturedBean.RetBean.HotSearchListBean>) getIntent().getSerializableExtra("hotSearchList");

    }

    @Override
    public void onSuccess(Object success) {

    }

    @Override
    SearchPresenter initPresenter() {
        return new SearchPresenter();
    }

    @Override
    int setChildContentView() {
        return R.layout.activity_search;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().detachView();
    }
}
