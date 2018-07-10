package com.example.weiying.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.weiying.R;
import com.example.weiying.presenter.MainPresenter;
import com.example.weiying.util.component.DaggerMainPresenterComponent;
import com.example.weiying.view.fragment.FindFragment;
import com.example.weiying.view.fragment.MyFragment;
import com.example.weiying.view.fragment.SceneFragment;
import com.example.weiying.view.fragment.SiftFragment;
import com.hjm.bottomtabbar.BottomTabBar;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<MainPresenter> {
    
    @Inject
    MainPresenter mainPresenter;
    private BottomTabBar mMainTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }
    @Override
    void initView() {
        DaggerMainPresenterComponent.builder()
                .build()
                .inject(this);
        mMainTabBar = (BottomTabBar) findViewById(R.id.main_TabBar);
    }
    @Override
    void initData() {
        mMainTabBar.init(getSupportFragmentManager())
                .setImgSize(60,60)
                .setFontSize(20)
                .setTabPadding(20,12,0)
                .setChangeColor(Color.RED,Color.GRAY)
                .addTabItem("精选",R.drawable.found_select,R.drawable.found, SiftFragment.class)
                .addTabItem("专题",R.drawable.special_select,R.drawable.special, FindFragment.class)
                .addTabItem("发现",R.drawable.fancy_select,R.drawable.fancy, SiftFragment.class)
                .addTabItem("现场",R.drawable.shipin,R.drawable.video, SceneFragment.class)
                .addTabItem("我的",R.drawable.my_select,R.drawable.my, MyFragment.class)
                .setTabBarBackgroundResource(R.drawable.bottom_bg)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name, View view) {
                    }
                });
    }
    @Override
    MainPresenter setBasePresenter() {
        return mainPresenter;
    }
    
    @Override
    int setChildContentView() {
        return R.layout.activity_main;
    }
}
