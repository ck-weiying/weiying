package com.example.weiying.view.fragment;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.weiying.R;
import com.example.weiying.model.bean.FeaturedBean;
import com.example.weiying.presenter.FeaturedPresenter;
import com.example.weiying.view.adapter.FeaturedAdapter;
import com.example.weiying.view.customview.ColorRelativeLayout;
import com.example.weiying.view.customview.ColorTextView;
import com.example.weiying.view.interfaces.IFeaturedView;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * 精选
 * author:Created by WangZhiQiang on 2018/7/9.
 */
public class FeaturedFragment extends BaseFragment<FeaturedPresenter> implements IFeaturedView, View.OnClickListener {

    private NestedScrollView featured_nslv;
    private XBanner xbanner;
    private RelativeLayout featured_search_bar;
    private RecyclerView featured_rv;
    private ColorRelativeLayout title;
    private ColorTextView title_name;

    private FeaturedBean featuredBean=new FeaturedBean();
    private List<FeaturedBean.RetBean.ListBean> contentList = new ArrayList<>();
    private FeaturedAdapter featuredAdapter;
    private int mScrollY;
    private List<String> list = new ArrayList<>();

    @Override
    void initView(View view) {
        featured_nslv = view.findViewById(R.id.featured_nslv);
        xbanner = view.findViewById(R.id.xbanner);
        featured_search_bar = view.findViewById(R.id.featured_search_bar);
        featured_rv = view.findViewById(R.id.featured_rv);
        title = view.findViewById(R.id.title);
        title_name = view.findViewById(R.id.title_name);

        featured_search_bar.setOnClickListener(this);
    }

    @Override
    void initData() {
        title_name.setText("精选");
        featured_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        featured_rv.setNestedScrollingEnabled(false);
        featuredAdapter = new FeaturedAdapter(getActivity());
        featured_rv.setAdapter(featuredAdapter);

        featured_nslv.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            int lastScrollY = 0;
            int h = DensityUtil.dp2px(300);
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (lastScrollY < h) {
                    scrollY = Math.min(h, scrollY);
                    mScrollY = scrollY > h ? h : scrollY;
                    title.setAlpha(1f * mScrollY / h);
                }
                lastScrollY = scrollY;
            }
        });
        title.setAlpha(0);
        getPresenter().getDataFromServer();
    }

    @Override
    public void onSuccess(Object success) {
        featuredBean= (FeaturedBean) success;
        for (int i = 0; i < featuredBean.getRet().getList().get(0).getChildList().size(); i++) {
            if (!(TextUtils.isEmpty(featuredBean.getRet().getList().get(0).getChildList().get(i).getPic()))){
                list.add(featuredBean.getRet().getList().get(0).getChildList().get(i).getPic());
            }
//            list.add(xbanner_list.get(i).getChildList().get(0).getPic().equals("") ? xbanner_list.get(i + 1).getChildList().get(0).getPic() : xbanner_list.get(i).getChildList().get(0).getPic());
        }
        xbanner.setData(list, null);
        xbanner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Glide.with(getActivity()).load(list.get(position)).into((ImageView) view);
            }
        });
        contentList.add(featuredBean.getRet().getList().get(2));
        contentList.add(featuredBean.getRet().getList().get(1));
        contentList.add(featuredBean.getRet().getList().get(4));
        contentList.add(featuredBean.getRet().getList().get(6));
        contentList.add(featuredBean.getRet().getList().get(7));
        featuredAdapter.setData(contentList);
    }

    @Override
    public void onClick(View v) {
//        startActivity(new Intent(getActivity(), DetailsActivity.class));
    }

    @Override
    FeaturedPresenter initPresenter() {
        return new FeaturedPresenter();
    }

    @Override
    int setChildContentView() {
        return R.layout.fragment_featured;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPresenter().detachView();
    }

    @Override
    public void onResume() {
        super.onResume();
        xbanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        xbanner.stopAutoPlay();
    }
}
