package com.example.weiying.presenter;

import com.example.weiying.model.api.RetrofitInterface;
import com.example.weiying.model.bean.LiveBean;
import com.example.weiying.model.http.RetrofitUtil;
import com.example.weiying.view.interfaces.IPlayStreamView;

/**
 * author:Created by WangZhiQiang on 2018/7/19.
 */
public class PlayStreamPresenter extends BasePresenter<IPlayStreamView>{
    private RetrofitInterface retrofitInterface;

    public PlayStreamPresenter() {
        retrofitInterface = RetrofitUtil.getInstance().getRetrofitInterface();
    }

    public void getDataFromServer() {
        getView().onSuccess(new LiveBean());
    }
}
