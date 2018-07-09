package com.example.weiying.util.component;

import com.example.weiying.view.activity.MainActivity;

import dagger.Component;

@Component
public interface MainPresenterComponent {
    void inject(MainActivity mainActivity);
}
