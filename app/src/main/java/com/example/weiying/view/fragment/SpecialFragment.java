package com.example.weiying.view.fragment;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.weiying.R;
import com.example.weiying.model.bean.SpecialBean;
import com.example.weiying.presenter.BasePresenter;
import com.example.weiying.presenter.SpecialPresenter;
import com.example.weiying.util.component.DaggerMainPresenterComponent;
import com.example.weiying.view.adapter.SpecialAdapter;
import com.example.weiying.view.customview.ColorRelativeLayout;
import com.example.weiying.view.interfaces.ISpecialView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * 专题页面
 */
public class SpecialFragment extends BaseFragment<SpecialPresenter> implements ISpecialView {
    /**
     * 标题
     */
    private ColorRelativeLayout mTitle;
    private RecyclerView mSpecialRecycler;
    private SmartRefreshLayout mSpecialSmart;
    private int page=0;
    private int total;
    @Inject
    SpecialPresenter specialPresenter;
    private SpecialAdapter specialAdapter;
    private SpecialBean specialBean=new SpecialBean();
    private TextView mTitle_name;
    private List<SpecialBean.RetBean.ListBean.ChildListBean> list=new ArrayList<>();
    @Override
    void initView(View view) {
        mTitle = (ColorRelativeLayout) view.findViewById(R.id.title);
        mTitle_name =(TextView) view.findViewById(R.id.title_name);
        mSpecialRecycler = (RecyclerView) view.findViewById(R.id.special_recycler);
        mSpecialSmart = (SmartRefreshLayout) view.findViewById(R.id.special_Smart);
        specialAdapter = new SpecialAdapter(getActivity());
        mSpecialRecycler.setAdapter(specialAdapter);
        mSpecialSmart.setEnableAutoLoadMore(false);
        DaggerMainPresenterComponent
                .builder()
                .build()
                .inject(this);
    }
    @Override
    void initData() {
        specialPresenter.getSpecialPresenter();
       //设置标题
       mTitle_name.setText("专题");
       mTitle.setBackgroundColor(Color.BLUE);
        //下拉刷新
       mSpecialSmart.setOnRefreshListener(new OnRefreshListener() {
           @Override
           public void onRefresh(@NonNull RefreshLayout refreshLayout) {
               page=1;
               specialPresenter.getSpecialPresenter();
           }
       });
        //上拉加载
        mSpecialSmart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                ++page;
                if (page <= ((total+4)/5)){
                    specialPresenter.getSpecialPresenter();
                }else {
                    mSpecialSmart.finishLoadMore();
                }
            }
        });
    }
    @Override
    SpecialPresenter setFragments() {
        return specialPresenter;
    }


    @Override
    int setChildContenView() {
        return R.layout.activity_special;
    }


    @Override
    public void onSuccess(SpecialBean specialBean) {
        this.specialBean=specialBean;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mSpecialRecycler.setLayoutManager(gridLayoutManager);
       if(specialBean.getRet().getList()!=null){
          specialAdapter.getData(specialBean.getRet().getList()); 
       }else {
           specialAdapter.getDataClear(null);
       }
        mSpecialSmart.finishRefresh();
        mSpecialSmart.finishLoadMore();
    }
    @Override
    public void Failed(String error) {
        Log.e("TAG",""+error);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        specialPresenter.detachView();
    }
}
