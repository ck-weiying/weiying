package com.example.weiying.presenter;

import com.example.weiying.model.api.RetrofitInterface;
import com.example.weiying.model.bean.LiveBean;
import com.example.weiying.model.http.RetrofitLiveUtil;
import com.example.weiying.view.interfaces.ILiveView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 专题
 * author:Created by WangZhiQiang on 2018/7/6.
 */
public class LivePresenter extends BasePresenter<ILiveView>{
    private RetrofitInterface retrofitInterface;

    public LivePresenter() {
        retrofitInterface = RetrofitLiveUtil.getInstance().getRetrofitInterface();
    }

    public void getDataFromServer() {
        Observable<LiveBean> liveBean = retrofitInterface.getLiveBean();
        liveBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LiveBean>() {
                    @Override
                    public void onSubscribe(Disposable d) { }
                    @Override
                    public void onNext(LiveBean liveBean) {
                        getView().onSuccess(liveBean);
                    }
                    @Override
                    public void onError(Throwable e) { }
                    @Override
                    public void onComplete() { }
                });
    }
}
