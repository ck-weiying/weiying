package com.example.weiying.presenter;

import com.example.weiying.model.api.RetrofitInterface;
import com.example.weiying.model.bean.DetailsBean;
import com.example.weiying.model.http.RetrofitUtil;
import com.example.weiying.view.interfaces.IDetailsView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 详情
 * author:Created by WangZhiQiang on 2018/7/6.
 */
public class DetailsPresenter extends BasePresenter<IDetailsView>{
    private RetrofitInterface retrofitInterface;

    public DetailsPresenter() {
        retrofitInterface = RetrofitUtil.getInstance().getRetrofitInterface();
    }

    public void getDataFromServer(String mediaId) {
        Observable<DetailsBean> detailsBean = retrofitInterface.getDetailsBean(mediaId);
        detailsBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) { }
                    @Override
                    public void onNext(DetailsBean detailsBean) {
                        if (detailsBean.getRet() != null) {
                            getView().onSuccess(detailsBean);
                        }
                    }
                    @Override
                    public void onError(Throwable e) { }
                    @Override
                    public void onComplete() {
                    }
                });
    }
}

