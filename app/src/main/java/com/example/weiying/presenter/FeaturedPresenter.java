package com.example.weiying.presenter;

import android.text.TextUtils;

import com.example.weiying.model.api.RetrofitInterface;
import com.example.weiying.model.bean.FeaturedBean;
import com.example.weiying.model.http.RetrofitUtil;
import com.example.weiying.view.interfaces.IFeaturedView;

import java.util.ArrayList;
import java.util.List;

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
                        List<String> list = new ArrayList<>();
                        List<String> listUrl = new ArrayList<>();
                        List<FeaturedBean.RetBean.ListBean> contentList = new ArrayList<>();
                        for (int i = 0; i < featuredBean.getRet().getList().get(0).getChildList().size(); i++) {
                            if (!((TextUtils.isEmpty(featuredBean.getRet().getList().get(0).getChildList().get(i).getPic())) || (TextUtils.isEmpty(featuredBean.getRet().getList().get(0).getChildList().get(i).getDataId())))){
                                list.add(featuredBean.getRet().getList().get(0).getChildList().get(i).getPic());
                                listUrl.add(featuredBean.getRet().getList().get(0).getChildList().get(i).getDataId());
                            }
                        }
                        contentList.add(featuredBean.getRet().getList().get(2));
                        contentList.add(featuredBean.getRet().getList().get(1));
                        contentList.add(featuredBean.getRet().getList().get(4));
                        contentList.add(featuredBean.getRet().getList().get(6));
                        contentList.add(featuredBean.getRet().getList().get(7));

                        getView().onSuccess(list,listUrl,contentList, featuredBean.getRet().getHotSearchList());
                    }
                    @Override
                    public void onError(Throwable e) { }
                    @Override
                    public void onComplete() { }
                });
    }
}

