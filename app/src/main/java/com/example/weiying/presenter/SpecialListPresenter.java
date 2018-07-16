package com.example.weiying.presenter;
import android.util.Log;

import com.example.weiying.model.bean.SpecialListBean;
import com.example.weiying.model.network.RetrofitApi;
import com.example.weiying.model.network.RetrofitUtils;
import com.example.weiying.view.interfaces.ISpecialListView;
import javax.inject.Inject;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SpecialListPresenter extends BasePresenter<ISpecialListView> {
    
    private final RetrofitApi retrofitApi;
    @Inject
    public SpecialListPresenter(){
        retrofitApi = RetrofitUtils.retrofitApi;
    }
    public  void getSpecialListPresenter(String catalogId){
        Observable<SpecialListBean> observable = retrofitApi.getSpecialList(catalogId);
        observable.subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new Consumer<SpecialListBean>() {
                      @Override
                      public void accept(SpecialListBean specialListBean) throws Exception {
                          String code = specialListBean.getCode();
                          Log.e("url",specialListBean.getMsg());
                          if(code.equals("200")){
                              SpecialListBean.RetBean ret = specialListBean.getRet();
                              getMiBaseView().onSuccess(ret);
                          }
                             
                      }
                  });

    }
}
