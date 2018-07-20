package com.example.weiying.presenter;

import com.example.weiying.model.api.RetrofitInterface;
import com.example.weiying.model.bean.FeaturedBean;
import com.example.weiying.model.http.RetrofitUtil;
import com.example.weiying.view.interfaces.IDiscoveryView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 发现
 * author:Created by WangZhiQiang on 2018/7/6.
 */
public class DiscoveryPresenter extends BasePresenter<IDiscoveryView>{
    private RetrofitInterface retrofitInterface;

    public DiscoveryPresenter() {
        retrofitInterface = RetrofitUtil.getInstance().getRetrofitInterface();
    }

    protected void hidLoading(){

    }

    public void getDataFromServer() {
        Observable<FeaturedBean> featuredBean = retrofitInterface.getFeaturedBean();
        featuredBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FeaturedBean>() {
                    @Override
                    public void onSubscribe(Disposable d) { }
                    @Override
                    public void onNext(FeaturedBean featuredBean) {
                        if (featuredBean.getRet() != null) {
                            getView().onSuccess(featuredBean);
                        }
                    }
                    @Override
                    public void onError(Throwable e) { }
                    @Override
                    public void onComplete() {
                        getView().hidLoading();
                    }
                });
    }
}
