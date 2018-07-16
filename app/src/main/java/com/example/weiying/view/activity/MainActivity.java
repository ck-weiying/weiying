package com.example.weiying.view.activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import com.example.weiying.R;
import com.example.weiying.presenter.MainPresenter;
import com.example.weiying.util.component.DaggerMainPresenterComponent;
import com.example.weiying.view.fragment.FindFragment;
import com.example.weiying.view.fragment.MyFragment;
import com.example.weiying.view.fragment.SceneFragment;
import com.example.weiying.view.fragment.SiftFragment;
import com.example.weiying.view.fragment.SpecialFragment;
import com.hjm.bottomtabbar.BottomTabBar;
import javax.inject.Inject;

public class MainActivity extends BaseActivity<MainPresenter> {
    @Inject
    MainPresenter mainPresenter;
    private BottomTabBar mMainTabBar;
    private LinearLayout mMain_Linear;
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
        mMain_Linear =(LinearLayout) findViewById(R.id.main_Linear);
    }
    @Override
    void initData() {
        mMainTabBar.init(getSupportFragmentManager(),720,1280)
                .setImgSize(47,43)
                .setFontSize(20)
                .setTabPadding(20,12,10)
                .setChangeColor(Color.RED,Color.GRAY)
                .addTabItem("精选",R.mipmap.found_select,R.mipmap.found, SiftFragment.class)
                .addTabItem("专题",R.mipmap.special_select,R.mipmap.special, SpecialFragment.class)
                .addTabItem("发现",R.mipmap.fancy_select,R.mipmap.fancy, FindFragment.class)
                .addTabItem("现场",R.mipmap.shipin,R.mipmap.video, SceneFragment.class)
                .addTabItem("我的",R.mipmap.my_select,R.mipmap.my, MyFragment.class)
                .setTabBarBackgroundResource(R.mipmap.bottom_bg)
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
    