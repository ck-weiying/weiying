package com.example.weiying.utils;

import android.content.Context;
import android.view.View;
import android.view.animation.TranslateAnimation;

/**
 * Created by dell on 2018/7/13.
 */

public class TransAnim {
    static TranslateAnimation translateAnimation;
    public static void Togglefrag(View view,int Width) {
        translateAnimation=new TranslateAnimation(0f,Width,0f,0f);
        translateAnimation.setDuration(1000);
        translateAnimation.setFillAfter(true);
        view.startAnimation(translateAnimation);
    }



    public static void toFir(boolean ispostion, final Context context, int distance, View v) {
        if (ispostion) {
            show(distance);
        } else {
            hide(distance);
        }
        translateAnimation.setDuration(1000);
        translateAnimation.setFillAfter(true);
        v.startAnimation(translateAnimation);

    }

    private static void hide(int distance) {
        translateAnimation = new TranslateAnimation(0f, 0, 0f, 0 - distance);
    }

    public static void show(int distance) {
        translateAnimation = new TranslateAnimation(0f, 0, 0 - distance, 0f);
    }
}
