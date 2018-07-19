package com.example.weiying.model.api;

import com.example.weiying.application.Constants;
import com.example.weiying.model.bean.CommentBean;
import com.example.weiying.model.bean.DetailsBean;
import com.example.weiying.model.bean.FeaturedBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

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
        //精选
        @GET(Constants.FEATURED_DATA_PATH)
        Observable<FeaturedBean> getFeaturedBean();
        //详情
        @GET(Constants.DETAILS_PATH)
        Observable<DetailsBean> getDetailsBean(@Query("mediaId")String mediaId);
        @GET(Constants.COMMENT_PATH)
        Observable<CommentBean> getCommentBean(@Query("mediaId")String mediaId);
}
