package com.example.weiying.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.weiying.R;
import com.example.weiying.model.bean.SpecialBean;
import com.example.weiying.presenter.SpecialListPresenter;
import com.example.weiying.util.component.DaggerMainPresenterComponent;
import com.example.weiying.view.adapter.SpecialListAdapter;
import com.example.weiying.view.customview.ColorRelativeLayout;
import com.example.weiying.view.customview.ColorTextView;
import com.example.weiying.view.interfaces.ISpecialListView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

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
    }

    @Override
    void initData() {
        specialListPresenter.getSpecialListPresenter();
        mTitleText.setText("列表"); 
        mTitle.setBackgroundColor(Color.BLUE);
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
    public void onSuccess(SpecialBean specialBean) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(SpecialListActivity.this, 3);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mSpecialRecyclerView.setLayoutManager(gridLayoutManager);
        List<SpecialBean.RetBean.ListBean.ChildListBean> list = new ArrayList<>();
        List<SpecialBean.RetBean.ListBean.ChildListBean> childList = specialBean.getRet().getList().get(0).getChildList();
        list.addAll(childList);
        specialListAdapter = new SpecialListAdapter(SpecialListActivity.this, list);
        mSpecialRecyclerView.setAdapter(specialListAdapter);
    }

    @Override
    public void Failed(String error) {

    }
}
