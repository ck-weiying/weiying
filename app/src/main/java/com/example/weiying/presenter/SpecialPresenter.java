package com.example.weiying.presenter;

import com.example.weiying.model.bean.SpecialBean;
import com.example.weiying.model.network.RetrofitApi;
import com.example.weiying.model.network.RetrofitUtils;
import com.example.weiying.view.interfaces.ISpecialView;

import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SpecialPresenter  extends BasePresenter <ISpecialView>{
    private final RetrofitApi retrofitApi;
    @Inject
    public SpecialPresenter(){
        retrofitApi = RetrofitUtils.retrofitApi;
    }
    public  void getSpecialPresenter(){
        Observable<SpecialBean> observable = retrofitApi.getSpecial();
        observable.subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Consumer<SpecialBean>() {
        @Override
        public void accept(SpecialBean specialBean) throws Exception {
               
            getMiBaseView().onSuccess(specialBean);
               
        }
    });
}
}
