package com.example.weiying.model.api;

import com.example.weiying.application.Constants;
import com.example.weiying.model.bean.FeaturedBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * author:Created by WangZhiQiang on 2018/7/6.
 */
public interface RetrofitInterface {
//    @GET("nba/?key=71e58b5b2f930eaf1f937407acde08fe&")
//    Observable<JavaBean> getCall(@Query("num") int num);
//
//    @POST("product/getCarts")
//    @FormUrlEncoded
//    Observable<CartBean> getCartBean(@FieldMap HashMap<String, String> map);
        @GET(Constants.FEATURED_DATA_PATH)
        Observable<FeaturedBean> getFeaturedBean();
}
