package com.example.weiying.presenter;

import com.example.weiying.model.api.RetrofitInterface;
import com.example.weiying.model.bean.CommentBean;
import com.example.weiying.model.http.RetrofitUtil;
import com.example.weiying.view.interfaces.ICommentView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 评论
 * author:Created by WangZhiQiang on 2018/7/6.
 */
public class CommentPresenter extends BasePresenter<ICommentView>{
    private RetrofitInterface retrofitInterface;

    public CommentPresenter() {
        retrofitInterface = RetrofitUtil.getInstance().getRetrofitInterface();
    }

    public void getDataFromServer(String mediaId) {
        Observable<CommentBean> featuredBean = retrofitInterface.getCommentBean(mediaId);
        featuredBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) { }
                    @Override
                    public void onNext(CommentBean commentBean) {
                        getView().onSuccess(commentBean);
                    }
                    @Override
                    public void onError(Throwable e) { }
                    @Override
                    public void onComplete() { }
                });
    }
}

