package com.example.weiying.presenter;

import com.example.weiying.model.api.RetrofitInterface;
import com.example.weiying.model.bean.CloseLiveBean;
import com.example.weiying.model.bean.OpenLiveBean;
import com.example.weiying.model.http.RetrofitLiveUtil;
import com.example.weiying.view.interfaces.IPushStreamView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * author:Created by WangZhiQiang on 2018/7/19.
 */
public class PushStreamPresenter extends BasePresenter<IPushStreamView>{
    private RetrofitInterface retrofitInterface;

    public PushStreamPresenter() {
        retrofitInterface = RetrofitLiveUtil.getInstance().getRetrofitInterface();
    }

    public void getOpenLive(int userId) {
        Observable<OpenLiveBean> openLiveBean = retrofitInterface.getOpenLiveBean(userId);
        openLiveBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OpenLiveBean>() {
                    @Override
                    public void onSubscribe(Disposable d) { }
                    @Override
                    public void onNext(OpenLiveBean openLiveBean) {
                        getView().onSuccess(openLiveBean,1);
                    }
                    @Override
                    public void onError(Throwable e) { }
                    @Override
                    public void onComplete() { }
                });
    }

    public void getCloseLive(int userId) {
        Observable<CloseLiveBean> closeLiveBean = retrofitInterface.getCloseLiveBean(userId);
        closeLiveBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CloseLiveBean>() {
                    @Override
                    public void onSubscribe(Disposable d) { }
                    @Override
                    public void onNext(CloseLiveBean closeLiveBean) {
                        getView().onSuccess(closeLiveBean,2);
                    }
                    @Override
                    public void onError(Throwable e) { }
                    @Override
                    public void onComplete() { }
                });
    }

    public void getDataFromServer(String userId) {

    }
}
