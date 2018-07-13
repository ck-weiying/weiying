package com.example.weiying.presenter;

import com.example.weiying.model.api.RetrofitInterface;
import com.example.weiying.model.bean.FeaturedBean;
import com.example.weiying.model.http.RetrofitUtil;
import com.example.weiying.view.interfaces.IFeaturedView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 精选
 * author:Created by WangZhiQiang on 2018/7/6.
 */
public class FeaturedPresenter extends BasePresenter<IFeaturedView>{
    private RetrofitInterface retrofitInterface;

    public FeaturedPresenter() {
        retrofitInterface = RetrofitUtil.getInstance().getRetrofitInterface();
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
                        getView().onSuccess(featuredBean);
                    }
                    @Override
                    public void onError(Throwable e) { }
                    @Override
                    public void onComplete() { }
                });
    }
}

