package com.example.weiying.presenter;

import com.example.weiying.view.interfaces.IBaseView;

public class BasePresenter <P extends IBaseView> {
    private  P miBaseView;

    public  void attachView(P iBaseView){
        this.miBaseView=iBaseView;
    }
    public  void  detachView(){
        miBaseView=null;
    }
    public  P getMiBaseView(){
        return  miBaseView;
    }
}
