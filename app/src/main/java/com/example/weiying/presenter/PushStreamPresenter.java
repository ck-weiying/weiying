package com.example.weiying.presenter;

import com.example.weiying.model.api.RetrofitInterface;
import com.example.weiying.model.http.RetrofitUtil;
import com.example.weiying.view.interfaces.IPushStreamView;

/**
 * author:Created by WangZhiQiang on 2018/7/19.
 */
public class PushStreamPresenter extends BasePresenter<IPushStreamView>{
    private RetrofitInterface retrofitInterface;

    public PushStreamPresenter() {
        retrofitInterface = RetrofitUtil.getInstance().getRetrofitInterface();
    }

    public void getDataFromServer(String mediaId) {

    }
}
