package com.example.weiying.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.weiying.R;
import com.example.weiying.model.bean.SpecialListBean;
import com.example.weiying.presenter.SpecialListPresenter;
import com.example.weiying.util.component.DaggerMainPresenterComponent;
import com.example.weiying.view.adapter.SpecialListAdapter;
import com.example.weiying.view.customview.ColorRelativeLayout;
import com.example.weiying.view.customview.ColorTextView;
import com.example.weiying.view.interfaces.ISpecialListView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class SpecialListActivity extends BaseActivity<SpecialListPresenter> implements ISpecialListView, View.OnClickListener {
    /**
     * 标题
     */
    private RecyclerView mSpecialRecyclerView;
    private SmartRefreshLayout mSpecialSmartRefreshLayout;
    private LinearLayout mSpecialListLinear;
    @Inject
    SpecialListPresenter specialListPresenter;
    private SpecialListAdapter specialListAdapter;
    private RelativeLayout mRlBack;
    private ColorTextView mTitleText;
    private ImageView mIvCollect;
    private RelativeLayout mRlCollect;
    private RelativeLayout mRlCollectClear;
    private ColorRelativeLayout mTitle;
    private ImageView mRl_close;
    private String catalogId;
    private int page=0;
    private int total;
    private SpecialListBean.RetBean ret;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    void initView() {
        mSpecialRecyclerView = (RecyclerView) findViewById(R.id.special_recyclerView);
        mSpecialSmartRefreshLayout = (SmartRefreshLayout) findViewById(R.id.special_SmartRefreshLayout);
        mSpecialListLinear = (LinearLayout) findViewById(R.id.special_list_Linear);
        DaggerMainPresenterComponent
                .builder()
                .build()
                .inject(this);
        mRlBack = (RelativeLayout) findViewById(R.id.rl_back);
        mRl_close = (ImageView)findViewById(R.id.rl_close);
        mTitleText = (ColorTextView) findViewById(R.id.title_text);
        mIvCollect = (ImageView) findViewById(R.id.iv_collect);
        mRlCollect = (RelativeLayout) findViewById(R.id.rl_collect);
        mRlCollectClear = (RelativeLayout) findViewById(R.id.rl_collect_clear);
        mTitle = (ColorRelativeLayout) findViewById(R.id.title);
        mRl_close.setOnClickListener(this);
        mSpecialRecyclerView.setOnClickListener(this);
        mSpecialSmartRefreshLayout.setOnClickListener(this);
        mSpecialListLinear.setOnClickListener(this);
        intent =getIntent();
        catalogId= intent.getStringExtra("catalogId");
    }
    @Override
    void initData() {
        specialListPresenter.getSpecialListPresenter(catalogId);
        mTitleText.setText(intent.getStringExtra("title"));
        Log.e("TAG",catalogId);
        mTitle.setBackgroundColor(Color.BLUE);
        //下拉刷新
        mSpecialSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page=1;
                specialListPresenter.getSpecialListPresenter(catalogId);
            }
        });
        //上拉加载
        mSpecialSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                ++page;
                if (page <= ((total+4)/5)){
                    specialListPresenter.getSpecialListPresenter(catalogId);
                }else {
                    mSpecialSmartRefreshLayout.finishLoadMore();
                }
            }
        });
    }

    @Override
    SpecialListPresenter setBasePresenter() {
        return specialListPresenter;
    }

    @Override
    int setChildContentView() {
        return R.layout.activity_special_list;
    }

    @Override
    public void onClick(View view) {
       switch (view.getId()){
           case R.id.rl_close:
                finish(); 
                break;
       }
    }
    @Override
    public void onSuccess(SpecialListBean.RetBean ret) {
        this.ret=ret;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(SpecialListActivity.this, 3);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mSpecialRecyclerView.setLayoutManager(gridLayoutManager);
        specialListAdapter = new SpecialListAdapter(SpecialListActivity.this,ret.getList());
        mSpecialRecyclerView.setAdapter(specialListAdapter);
//        if(ret.getList()!=null){
//            specialListAdapter.setData(ret.getList());
//        }else {
//            specialListAdapter.setDataClear(null);
//        }
//        mSpecialSmartRefreshLayout.finishRefresh();
//        mSpecialSmartRefreshLayout.finishLoadMore();
    }
    @Override
    public void Failed(String error) {

    }
}
