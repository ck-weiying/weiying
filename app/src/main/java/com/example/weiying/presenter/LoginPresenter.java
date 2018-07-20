package com.example.weiying.presenter;

import com.example.weiying.model.api.RetrofitInterface;
import com.example.weiying.model.bean.LoginBean;
import com.example.weiying.model.http.RetrofitLiveUtil;
import com.example.weiying.view.interfaces.ILoginView;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * author:Created by WangZhiQiang on 2018/4/16.
 */
public class LoginPresenter extends BasePresenter<ILoginView>{

    private RetrofitInterface retrofitInterface;

    public LoginPresenter() {
        retrofitInterface = RetrofitLiveUtil.getInstance().getRetrofitInterface();
    }

    public void getDataFromServer(HashMap<String, String> map) {
        Observable<LoginBean> loginBean = retrofitInterface.getLoginBean(map);
        loginBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) { }
                    @Override
                    public void onNext(LoginBean loginBean) {
                        getView().onSuccess(loginBean);
                    }
                    @Override
                    public void onError(Throwable e) { }
                    @Override
                    public void onComplete() { }
                });
    }
}
