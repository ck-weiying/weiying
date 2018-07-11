package com.example.weiying.presenter;

import com.example.weiying.model.api.RetrofitInterface;
import com.example.weiying.model.http.RetrofitUtil;
import com.example.weiying.view.interfaces.IMineView;

import java.util.HashMap;

/**
 * 我的
 * author:Created by WangZhiQiang on 2018/7/6.
 */
public class MinePresenter extends BasePresenter<IMineView>{
    private RetrofitInterface retrofitInterface;

    public MinePresenter() {
        retrofitInterface = RetrofitUtil.getInstance().getRetrofitInterface();
    }

    public void getDataFromServer(HashMap<String, String> map) {

    }
}

