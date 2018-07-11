package com.example.weiying.model.network;

import com.example.weiying.Bean.SiftFragBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

import static com.example.weiying.util.constant.Site.SIFTDATAPATH;

public interface RetrofitApi {
    @GET(SIFTDATAPATH)
    Flowable<SiftFragBean> siftFragData();
}
