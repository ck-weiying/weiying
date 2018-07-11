package com.example.weiying.presenter;

import com.example.weiying.model.api.RetrofitInterface;
import com.example.weiying.model.http.RetrofitUtil;
import com.example.weiying.view.interfaces.IDiscoveryView;

import java.util.HashMap;

/**
 * 发现
 * author:Created by WangZhiQiang on 2018/7/6.
 */
public class DiscoveryPresenter extends BasePresenter<IDiscoveryView>{
    private RetrofitInterface retrofitInterface;

    public DiscoveryPresenter() {
        retrofitInterface = RetrofitUtil.getInstance().getRetrofitInterface();
    }

    public void getDataFromServer(HashMap<String, String> map) {

    }
}

