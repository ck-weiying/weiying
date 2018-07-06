package com.example.weiying.presenter;

import com.example.weiying.view.interfaces.IBaseView;

/**
 * author:Created by WangZhiQiang on 2018/7/5.
 */
public class BasePresenter<T extends IBaseView> {

    private T t;

    public void attachView(T t){
        this.t=t;
    }

    public T getView(){
        return t;
    }

    public void detachView(){
        t=null;
    }
}
