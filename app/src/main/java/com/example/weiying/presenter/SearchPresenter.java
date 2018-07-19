package com.example.weiying.presenter;

import com.example.weiying.model.api.RetrofitInterface;
import com.example.weiying.model.http.RetrofitUtil;
import com.example.weiying.view.interfaces.ISearchView;

import java.util.HashMap;

/**
 * author:Created by WangZhiQiang on 2018/7/6.
 */
public class SearchPresenter extends BasePresenter<ISearchView>{
    private RetrofitInterface retrofitInterface;

    public SearchPresenter() {
        retrofitInterface = RetrofitUtil.getInstance().getRetrofitInterface();
    }

    public void getDataFromServer(HashMap<String, String> map) {

    }
}

