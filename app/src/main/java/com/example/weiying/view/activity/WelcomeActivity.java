package com.example.weiying.view.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.widget.ImageView;

import com.example.weiying.R;

import java.util.Random;

public class WelcomeActivity extends Activity {


    ImageView iv_entry;

    private static final int ANIM_TIME = 2000;

    private static final float SCALE_END = 1.15F;

    private static final int[] Imgs={R.drawable.a,R.drawable.b, R.drawable.c,R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
//        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.white), false);
        iv_entry = findViewById(R.id.iv_entry);

        Random random = new Random(SystemClock.elapsedRealtime());//SystemClock.elapsedRealtime() 从开机到现在的毫秒数（手机睡眠(sleep)的时间也包括在内）
        iv_entry.setImageResource(Imgs[random.nextInt(Imgs.length)]);
//        startAnim();
        setValueAnimator();
//        Observable.timer(5000, TimeUnit.MILLISECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Exception {
//                        startAnim();
//                    }
//                });
    }


    private void startAnim() {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(iv_entry, "scaleX", 1f, SCALE_END);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(iv_entry, "scaleY", 1f, SCALE_END);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(ANIM_TIME).play(animatorX).with(animatorY);
        set.start();
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                WelcomeActivity.this.finish();
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });
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
//                overridePendingTransition(0,0);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
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
