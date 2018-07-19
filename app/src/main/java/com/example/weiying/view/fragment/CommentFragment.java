package com.example.weiying.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.weiying.R;
import com.example.weiying.model.bean.CommentBean;
import com.example.weiying.presenter.CommentPresenter;
import com.example.weiying.view.adapter.CommentAdapter;
import com.example.weiying.view.interfaces.ICommentView;

import java.util.ArrayList;
import java.util.List;

/**
 * 讨论
 * author:Created by WangZhiQiang on 2018/7/16.
 */
public class CommentFragment extends BaseFragment<CommentPresenter> implements ICommentView{
    private RecyclerView comment_rv;
    private List<CommentBean.RetBean.ListBean> list = new ArrayList<>();
    private CommentAdapter commentAdapter;

    @Override
    void initView(View view) {
        comment_rv = view.findViewById(R.id.comment_rv);
    }

    @Override
    void initData() {
        getPresenter().getDataFromServer(getArguments().getString("dataID"));
    }

    @Override
    public void onSuccess(Object success) {
        list=((CommentBean)success).getRet().getList();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        comment_rv.setLayoutManager(linearLayoutManager);
        commentAdapter = new CommentAdapter(getActivity(), list);
        comment_rv.setAdapter(commentAdapter);
    }

    @Override
    CommentPresenter initPresenter() {
        return new CommentPresenter();
    }

    @Override
    int setChildContentView() {
        return R.layout.fragment_details_comment;
    }

    public static CommentFragment getInstances(String dataID) {
        CommentFragment fragmentTabs = new CommentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("dataID", dataID);
        fragmentTabs.setArguments(bundle);
        return fragmentTabs;
    }
}
