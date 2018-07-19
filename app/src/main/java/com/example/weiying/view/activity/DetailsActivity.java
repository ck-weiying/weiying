package com.example.weiying.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.weiying.R;
import com.example.weiying.model.bean.DetailsBean;
import com.example.weiying.presenter.DetailsPresenter;
import com.example.weiying.utils.FrescoUtil;
import com.example.weiying.view.fragment.CommentFragment;
import com.example.weiying.view.fragment.IntroductionFragment;
import com.example.weiying.view.interfaces.IDetailsView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.Debuger;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * 详情
 */
public class DetailsActivity extends BaseActivity<DetailsPresenter> implements IDetailsView{
    private String mediaId;
    private TabLayout detail_tabLayout;
    private ViewPager detail_vp;
    private StandardGSYVideoPlayer detail_player;

    private DetailsBean detailsBean=new DetailsBean();
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titles = new ArrayList<>();


    private boolean isPlay;
    private boolean isPause;
    private OrientationUtils orientationUtils;

    @Override
    void initView() {
        detail_tabLayout = findViewById(R.id.detail_tabLayout);
        detail_vp = findViewById(R.id.detail_vp);
        detail_player = findViewById(R.id.detail_player);

        mediaId=getIntent().getStringExtra("mediaId");
        Toast.makeText(this, mediaId, Toast.LENGTH_SHORT).show();
    }

    @Override
    void initData() {
        titles.add("简介");
        titles.add("评论");

        if (mediaId != null) {
            getPresenter().getDataFromServer(mediaId);
        }
    }

    @Override
    public void onSuccess(Object success) {
        detailsBean= (DetailsBean) success;
        String url;
        if (!TextUtils.isEmpty(detailsBean.getRet().getHDURL())){
            url=detailsBean.getRet().getHDURL();
        }else if (!TextUtils.isEmpty(detailsBean.getRet().getSDURL())){
            url=detailsBean.getRet().getSDURL();
        }else if (!TextUtils.isEmpty(detailsBean.getRet().getSmoothURL())){
            url=detailsBean.getRet().getSmoothURL();
        }else{
            return;
        }
        setIJKPlayer(url,detailsBean.getRet().getPic(),detailsBean.getRet().getTitle());

        fragmentList.add(IntroductionFragment.getInstances(detailsBean.getRet()));
        fragmentList.add(CommentFragment.getInstances(detailsBean.getRet().getDataID()));

        detail_vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }
            @Override
            public int getCount() {
                return titles.size();
            }
            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }
        });
        detail_tabLayout.setTabMode(TabLayout.MODE_FIXED);//显示模式   MODE_FIXED可实现选项居中
        //让viewpager和顶部标题关联
        detail_tabLayout.setupWithViewPager(detail_vp);
    }

    private void setIJKPlayer(String url,String icon,String title){
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(this);
        FrescoUtil.setJianJin(icon,simpleDraweeView);
        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils(this, detail_player);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);

        GSYVideoOptionBuilder gsyVideoOption = new GSYVideoOptionBuilder();
        gsyVideoOption.setThumbImageView(simpleDraweeView)
                .setIsTouchWiget(true)
                .setRotateViewAuto(false)
                .setLockLand(false)
                .setAutoFullWithSize(true)
                .setShowFullAnimation(false)
                .setNeedLockFull(true)
                .setUrl(url)
                .setCacheWithPlay(false)
                .setVideoTitle(title)
                .setVideoAllCallBack(new GSYSampleCallBack() {
                    @Override
                    public void onPrepared(String url, Object... objects) {
                        super.onPrepared(url, objects);
                        //开始播放了才能旋转和全屏
                        orientationUtils.setEnable(true);
                        isPlay = true;
                    }
                    @Override
                    public void onQuitFullscreen(String url, Object... objects) {
                        super.onQuitFullscreen(url, objects);
                        Debuger.printfError("***** onQuitFullscreen **** " + objects[0]);//title
                        Debuger.printfError("***** onQuitFullscreen **** " + objects[1]);//当前非全屏player
                        if (orientationUtils != null) {
                            orientationUtils.backToProtVideo();
                        }
                    }
                }).setLockClickListener(new LockClickListener() {
            @Override
            public void onClick(View view, boolean lock) {
                if (orientationUtils != null) {
                    //配合下方的onConfigurationChanged
                    orientationUtils.setEnable(!lock);
                }
            }
        }).build(detail_player);

        detail_player.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接横屏
                orientationUtils.resolveByClick();
                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                detail_player.startWindowFullscreen(DetailsActivity.this, true, true);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();
        }
        if (GSYVideoManager.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }


    @Override
    protected void onPause() {
        detail_player.getCurrentPlayer().onVideoPause();
        super.onPause();
        isPause = true;
    }

    @Override
    protected void onResume() {
        detail_player.getCurrentPlayer().onVideoResume(false);
        super.onResume();
        isPause = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isPlay) {
            detail_player.getCurrentPlayer().release();
        }
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
        if (isPlay && !isPause) {
            detail_player.onConfigurationChanged(this, newConfig, orientationUtils, true, true);
        }
    }

    @Override
    DetailsPresenter initPresenter() {
        return new DetailsPresenter();
    }

    @Override
    int setChildContentView() {
        return R.layout.activity_details;
    }

    public static void start(Context context,String mediaId) {
        Intent starter = new Intent(context, DetailsActivity.class);
        starter.putExtra("mediaId", mediaId);
        context.startActivity(starter);
    }
}
