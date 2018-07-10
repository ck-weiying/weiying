package com.example.weiying.view.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.ImageView;

import com.example.weiying.R;
import com.example.weiying.presenter.BasePresenter;

import java.util.Random;

public class WelcomeActivity extends AppCompatActivity {
    ImageView iv_entry;

    private static final int ANIM_TIME = 2000;

    private static final float SCALE_END = 1.15F;

    private static final int[] Imgs={R.drawable.a,R.drawable.b, R.drawable.c,R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        iv_entry = findViewById(R.id.iv_entry);
        Random random = new Random(SystemClock.elapsedRealtime());//SystemClock.elapsedRealtime() 从开机到现在的毫秒数（手机睡眠(sleep)的时间也包括在内）
        iv_entry.setImageResource(Imgs[random.nextInt(Imgs.length)]);

        setValueAnimator();
    }

    private void setValueAnimator() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1f, SCALE_END);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                iv_entry.setScaleX(value);
                iv_entry.setScaleY(value);
            }
        });
        valueAnimator.setDuration(ANIM_TIME);
        valueAnimator.start();
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                overridePendingTransition(0,0);
                WelcomeActivity.this.finish();
                super.onAnimationEnd(animation);
            }
        });
    }

    /**
     * 屏蔽物理返回按钮
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
