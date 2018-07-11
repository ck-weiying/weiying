package com.example.weiying.presenter;

import com.example.weiying.model.api.RetrofitInterface;
import com.example.weiying.model.http.RetrofitUtil;
import com.example.weiying.view.interfaces.IFeaturedView;

import java.util.HashMap;

/**
 * 精选
 * author:Created by WangZhiQiang on 2018/7/6.
 */
public class FeaturedPresenter extends BasePresenter<IFeaturedView>{
    private RetrofitInterface retrofitInterface;

    public FeaturedPresenter() {
        retrofitInterface = RetrofitUtil.getInstance().getRetrofitInterface();
    }

    public void getDataFromServer(HashMap<String, String> map) {

    }
}

