package com.example.weiying.application;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * author:Created by WangZhiQiang on 2018/7/6.
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
