package com.example.weiying.view.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.PowerManager;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.example.weiying.R;
import com.example.weiying.model.bean.LiveBean;
import com.example.weiying.presenter.PlayStreamPresenter;
import com.example.weiying.utils.MediaUtils;
import com.example.weiying.view.interfaces.IPlayStreamView;

import java.util.ArrayList;
import java.util.List;

public class PlayStreamActivity extends BaseActivity<PlayStreamPresenter> implements IPlayStreamView{
    private PlayerView player;
    private Context mContext;
    private View rootView;
    private List<LiveBean> list;
    private String url = "rtmp://172.17.8.100/live/xyj";
    private String title = "直播";
    private PowerManager.WakeLock wakeLock;

    @Override
    void initView() {
        this.mContext = this;
        rootView =getLayoutView();
        /**常亮*/
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "liveTAG");
        wakeLock.acquire();
    }

    @Override
    void initData() {
        final String url="http://7xi8d6.com1.z0.glb.clouddn.com/20180122090204_A4hNiG_Screenshot.jpeg";
        player = new PlayerView(this, rootView)
                .setTitle(title)
                .setScaleType(PlayStateParams.fitparent)
                .hideMenu(true)
                .hideSteam(true)
                .setForbidDoulbeUp(true)
                .hideCenterPlayer(true)
                .hideControlPanl(true)
                .showThumbnail(new OnShowThumbnailListener() {
                    @Override
                    public void onShowThumbnail(ImageView ivThumbnail) {
                        Glide.with(mContext)
                                .load(url)
                                .placeholder(R.color.black)
                                .error(R.color.white)
                                .into(ivThumbnail);
                    }
                });

        getPresenter().getDataFromServer();
    }

    @Override
    public void onSuccess(Object success) {
        list = new ArrayList<>();
        if (list.size() > 1) {
            url = list.get(1).getLiveStream();
            title = list.get(1).getNickname();
        }
        player.setPlaySource(url).startPlay();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player != null) {
            player.onPause();
        }
        MediaUtils.muteAudioFocus(mContext, true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (player != null) {
            player.onResume();
        }
        MediaUtils.muteAudioFocus(mContext, false);
        if (wakeLock != null) {
            wakeLock.acquire();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.onDestroy();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (player != null) {
            player.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onBackPressed() {
        if (player != null && player.onBackPressed()) {
            return;
        }
        super.onBackPressed();
        if (wakeLock != null) {
            wakeLock.release();
        }
    }

    @Override
    PlayStreamPresenter initPresenter() {
        return new PlayStreamPresenter();
    }

    @Override
    int setChildContentView() {
        return R.layout.simple_player_view_player;
    }
}
