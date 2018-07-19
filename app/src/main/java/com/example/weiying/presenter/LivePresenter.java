package com.example.weiying.presenter;

import com.example.weiying.model.api.RetrofitInterface;
import com.example.weiying.model.http.RetrofitUtil;
import com.example.weiying.view.interfaces.ILiveView;

import java.util.HashMap;

/**
 * 专题
 * author:Created by WangZhiQiang on 2018/7/6.
 */
public class LivePresenter extends BasePresenter<ILiveView>{
    private RetrofitInterface retrofitInterface;

    public LivePresenter() {
        retrofitInterface = RetrofitUtil.getInstance().getRetrofitInterface();
    }

    public void getDataFromServer(HashMap<String, String> map) {

    }
}

