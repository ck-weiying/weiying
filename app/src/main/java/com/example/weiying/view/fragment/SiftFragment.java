package com.example.weiying.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.weiying.Bean.SiftFragBean;
import com.example.weiying.R;
import com.example.weiying.adapter.SiftrecycleAdapter;
import com.example.weiying.model.network.RetrofitApi;
import com.example.weiying.model.network.RetrofitUtils;
import com.example.weiying.presenter.BasePresenter;
import com.example.weiying.util.application.MyApplication;
import com.example.weiying.view.activity.DetailsActivity;
import com.example.weiying.view.activity.getData;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * 精选页面
 */
public class SiftFragment extends BaseFragment implements getData {


    XBanner banner;
    List<String> list = new ArrayList<>();
    Unbinder unbinder;
    private RecyclerView recyclerView;
    private List<SiftFragBean.RetBean.ListBean> contentList = new ArrayList<>();
    private SiftrecycleAdapter siftrecycleAdapter;

    @Override
    void initView(View view) {
        banner = view.findViewById(R.id.xbanner);
        recyclerView = view.findViewById(R.id.sift_recycle);
    }

    @Override
    void initData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(MyApplication.app));
        recyclerView.setNestedScrollingEnabled(false);
        siftrecycleAdapter = new SiftrecycleAdapter(MyApplication.app, contentList);
        recyclerView.setAdapter(siftrecycleAdapter);
        getData(this);
    }

    @Override
    BasePresenter setFragments() {
        return null;
    }

    @Override
    int setChildContenView() {
        return R.layout.activity_sift;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void getData(final getData getData) {
        RetrofitApi retrofitApi = RetrofitUtils.retrofitApi;
        retrofitApi.siftFragData().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new DisposableSubscriber<SiftFragBean>() {
                    @Override
                    public void onNext(SiftFragBean siftFragBean) {
                        getData.scuess(siftFragBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void scuess(SiftFragBean siftFragBean) {
        List<SiftFragBean.RetBean.ListBean> list_arr = siftFragBean.getRet().getList();
        for (int i = 0; i < list_arr.size(); i++) {
            list.add(list_arr.get(i).getChildList().get(0).getPic().equals("") ? list_arr.get(i + 1).getChildList().get(0).getPic() : list_arr.get(i).getChildList().get(0).getPic());
        }
        banner.setData(list, null);
        banner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Glide.with(MyApplication.app).load(list.get(position)).into((ImageView) view);

            }

        });
        contentList.addAll(list_arr);
        siftrecycleAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.SIFT_search_bar)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), DetailsActivity.class));
    }
}
