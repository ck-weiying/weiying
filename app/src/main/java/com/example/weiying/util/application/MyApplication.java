package com.example.weiying.util.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;

public class MyApplication extends Application {
    public static MyApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        app = this;
    }


}
