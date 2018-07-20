package com.example.weiying.model.api;

import com.example.weiying.application.Constants;
import com.example.weiying.model.bean.CloseLiveBean;
import com.example.weiying.model.bean.CommentBean;
import com.example.weiying.model.bean.DetailsBean;
import com.example.weiying.model.bean.FeaturedBean;
import com.example.weiying.model.bean.LiveBean;
import com.example.weiying.model.bean.LoginBean;
import com.example.weiying.model.bean.OpenLiveBean;
import com.example.weiying.model.bean.RegisterBean;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

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
        //讨论
        @GET(Constants.COMMENT_PATH)
        Observable<CommentBean> getCommentBean(@Query("mediaId")String mediaId);
        //直播列表
        @GET(Constants.LIVE_PATH)
        Observable<LiveBean> getLiveBean();
        //开启直播
        @GET(Constants.OPEN_LIVE_PATH)
        Observable<OpenLiveBean> getOpenLiveBean(@Header("userId")int userId);
        //关闭直播
        @GET(Constants.CLOSE_LIVE_PATH)
        Observable<CloseLiveBean> getCloseLiveBean(@Header("userId")int userId);
        //注册
        @POST(Constants.REGISTER_LIVE_PATH)
        @FormUrlEncoded
        Observable<RegisterBean> getRegisterBean(@FieldMap HashMap<String, String> map);
        //登录
        @GET(Constants.LOGIN_LIVE_PATH)
        Observable<LoginBean> getLoginBean(@QueryMap HashMap<String, String> map);
}
