package com.example.weiying.presenter;

import com.example.weiying.model.api.RetrofitInterface;
import com.example.weiying.model.http.RetrofitUtil;
import com.example.weiying.view.interfaces.IFeaturesView;

import java.util.HashMap;

/**
 * 专题
 * author:Created by WangZhiQiang on 2018/7/6.
 */
public class FeaturesPresenter extends BasePresenter<IFeaturesView>{
    private RetrofitInterface retrofitInterface;

    public FeaturesPresenter() {
        retrofitInterface = RetrofitUtil.getInstance().getRetrofitInterface();
    }

    public void getDataFromServer(HashMap<String, String> map) {

    }
}

