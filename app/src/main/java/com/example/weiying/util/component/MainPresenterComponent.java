package com.example.weiying.util.component;

import com.example.weiying.view.activity.MainActivity;
import com.example.weiying.view.activity.SpecialListActivity;
import com.example.weiying.view.fragment.SpecialFragment;

import dagger.Component;

@Component
public interface MainPresenterComponent {
    void inject(MainActivity mainActivity);
    void inject(SpecialFragment specialFragment);
    void inject(SpecialListActivity specialListActivity);
}
