package com.example.weiying.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weiying.presenter.BasePresenter;
import com.example.weiying.view.interfaces.IBaseView;

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements IBaseView {
    public  T fragments;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(setChildContenView(),container,false);
        initView(view);
        initDataBase();
        initData();
        return view;
    }
    private void initDataBase() {
        fragments=setFragments();
        if(fragments!=null){
            fragments.attachView(this);
        }else {
            try {
                throw  new Exception("兄弟 prenter 没有设置 请在您的Fragment 创建 presenter！！！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    abstract void initView(View view);
    abstract void initData();
    abstract  T setFragments();
    abstract int setChildContenView();
}