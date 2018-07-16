package com.example.weiying.view.interfaces;
import com.example.weiying.model.bean.SpecialListBean;
public interface ISpecialListView extends IBaseView {
    void onSuccess(SpecialListBean.RetBean ret);
    void Failed(String error);
}
