package com.example.weiying.view.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.daprlabs.cardstack.SwipeFrameLayout;
import com.example.weiying.R;
import com.example.weiying.model.bean.FeaturedBean;
import com.example.weiying.presenter.DiscoveryPresenter;
import com.example.weiying.utils.ScreenUtil;
import com.example.weiying.view.adapter.SwipeDeckAdapter;
import com.example.weiying.view.customview.ColorTextView;
import com.example.weiying.view.customview.LVGhost;
import com.example.weiying.view.customview.SwipeDeck;
import com.example.weiying.view.interfaces.IDiscoveryView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 发现
 * author:Created by WangZhiQiang on 2018/7/9.
 */
public class DiscoveryFragment extends BaseFragment<DiscoveryPresenter> implements IDiscoveryView, View.OnClickListener {

    private ColorTextView titleName;
    private SwipeDeck swipeDeck;
    private SwipeFrameLayout swipeLayout;
    private LVGhost loading;
    private Button btn_next;
    private TextView tvNomore;

    private SwipeDeckAdapter adapter;
//    private List<VideoType> videos = new ArrayList<>();
    private FeaturedBean featuredBean=new FeaturedBean();
    private List<FeaturedBean.RetBean.ListBean> contentList = new ArrayList<>();
    private List<String> list = new ArrayList<>();

    @Override
    void initView(View view) {
        titleName = view.findViewById(R.id.title_name);
        swipeDeck = view.findViewById(R.id.swipe_deck);
        swipeLayout = view.findViewById(R.id.swipeLayout);
        loading = view.findViewById(R.id.loading);
        btn_next = view.findViewById(R.id.btn_next);
        tvNomore = view.findViewById(R.id.tv_nomore);
        btn_next.setOnClickListener(this);
        tvNomore.setOnClickListener(this);

        titleName.setText("发现");
        ViewGroup.LayoutParams lp = swipeDeck.getLayoutParams();
        lp.height = ScreenUtil.getScreenHeight(getContext()) / 3 * 2;
        swipeDeck.setLayoutParams(lp);
        swipeDeck.setHardwareAccelerationEnabled(true);
    }

    @Override
    void initData() {
        swipeDeck.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) { }
            @Override
            public void cardSwipedRight(int position) { }
            @Override
            public void cardsDepleted() {
                swipeDeck.setVisibility(View.GONE);
            }
            @Override
            public void cardActionDown() { }
            @Override
            public void cardActionUp() { }
        });
        getPresenter().getDataFromServer();
    }

    @Override
    public void onSuccess(Object success) {
        featuredBean= (FeaturedBean) success;
        if (featuredBean.getRet() != null) {
            contentList.clear();
            contentList.add(featuredBean.getRet().getList().get(0));
            contentList.add(featuredBean.getRet().getList().get(2));
            contentList.add(featuredBean.getRet().getList().get(1));
            contentList.add(featuredBean.getRet().getList().get(4));
            contentList.add(featuredBean.getRet().getList().get(6));
//            contentList.add(featuredBean.getRet().getList().get(7));
            swipeDeck.removeAllViews();
            swipeDeck.removeAllViews();
            adapter = new SwipeDeckAdapter(getData(),getContext());
            swipeDeck.setAdapter(adapter);
            tvNomore.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
            case R.id.tv_nomore:
                nextVideos();
                break;
        }
    }

    private List<FeaturedBean.RetBean.ListBean.ChildListBean> getData(){
        Random rand = new Random();
        int i = rand.nextInt(6);
        return contentList.get(i).getChildList();
    }

    private void nextVideos() {
        swipeDeck.setVisibility(View.VISIBLE);
        loading.setVisibility(View.VISIBLE);
        tvNomore.setVisibility(View.GONE);
        getPresenter().getDataFromServer();
    }

    @Override
    public void hidLoading() {
        loading.setVisibility(View.GONE);
    }

    @Override
    DiscoveryPresenter initPresenter() {
        return new DiscoveryPresenter();
    }

    @Override
    int setChildContentView() {
        return R.layout.fragment_discovery;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPresenter().detachView();
    }
}
