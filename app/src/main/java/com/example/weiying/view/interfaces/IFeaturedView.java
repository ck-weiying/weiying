package com.example.weiying.view.interfaces;

import com.example.weiying.model.bean.FeaturedBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 精选
 * author:Created by WangZhiQiang on 2018/7/9.
 */
public interface IFeaturedView extends IBaseView{
    void onSuccess(List<String> list, List<String> listUrl, List<FeaturedBean.RetBean.ListBean> contentList,ArrayList<FeaturedBean.RetBean.HotSearchListBean> hotSearchList);
}
