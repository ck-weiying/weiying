package com.example.weiying.view.interfaces;

import com.example.weiying.model.bean.SpecialBean;

public interface ISpecialListView extends IBaseView {
    void onSuccess(SpecialBean specialBean);
    void Failed(String error);
}
