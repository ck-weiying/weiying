package com.example.weiying.presenter;

import com.example.weiying.model.api.RetrofitInterface;
import com.example.weiying.model.bean.RegisterBean;
import com.example.weiying.model.http.RetrofitLiveUtil;
import com.example.weiying.view.interfaces.IRegisterView;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * author:Created by WangZhiQiang on 2018/5/18.
 */
public class RegisterPresenter extends BasePresenter<IRegisterView>{
    private RetrofitInterface retrofitInterface;

    public RegisterPresenter() {
        retrofitInterface = RetrofitLiveUtil.getInstance().getRetrofitInterface();
    }

    public void getDataFromServer(HashMap<String, String> map) {
        Observable<RegisterBean> registerBean = retrofitInterface.getRegisterBean(map);
        registerBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) { }
                    @Override
                    public void onNext(RegisterBean registerBean) {
                        getView().onSuccess(registerBean);
                    }
                    @Override
                    public void onError(Throwable e) { }
                    @Override
                    public void onComplete() { }
                });
    }
}
