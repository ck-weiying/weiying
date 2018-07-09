package com.example.weiying.model.network;

import com.example.weiying.util.constant.Site;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private static RetrofitUtils retrofitUtils;
    public static RetrofitApi retrofitApi;
    private RetrofitUtils() {
    }
    public RetrofitUtils getInstance(){

        if (retrofitUtils==null){

            retrofitUtils = new RetrofitUtils();
        }

        return retrofitUtils;
    }
    static {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Site.Site_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        retrofitApi = retrofit.create(RetrofitApi.class);
    }

}