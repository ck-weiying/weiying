package com.example.weiying.model.network;

import com.example.weiying.model.bean.SpecialBean;

import io.reactivex.Observable;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitApi {
    //专题
    @GET("front/homePageApi/homePage.do")
    Observable<SpecialBean> getSpecial();
}
