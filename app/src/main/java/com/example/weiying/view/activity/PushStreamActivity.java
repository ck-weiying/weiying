package com.example.weiying.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.weiying.R;
import com.example.weiying.model.bean.CloseLiveBean;
import com.example.weiying.model.bean.OpenLiveBean;
import com.example.weiying.presenter.PushStreamPresenter;
import com.example.weiying.view.interfaces.IPushStreamView;
import com.github.faucamp.simplertmp.RtmpHandler;
import com.seu.magicfilter.utils.MagicFilterType;

import net.ossrs.yasea.SrsCameraView;
import net.ossrs.yasea.SrsEncodeHandler;
import net.ossrs.yasea.SrsPublisher;
import net.ossrs.yasea.SrsRecordHandler;

import java.io.IOException;
import java.net.SocketException;

public class PushStreamActivity extends BaseActivity<PushStreamPresenter> implements IPushStreamView,SrsEncodeHandler.SrsEncodeListener, RtmpHandler.RtmpListener, SrsRecordHandler.SrsRecordListener, View.OnClickListener {
    private static final String TAG = "PushStreamActivity";

    private SrsCameraView cameraView;
    private SrsPublisher srsPublisher;

    private Button publish;
    private Button swCam;
    private Button swEnc;

    private String rtmpUrl;
    private int userId;
    private boolean close;
    private OpenLiveBean openLiveBean=new OpenLiveBean();
    private CloseLiveBean closeLiveBean=new CloseLiveBean();

    @Override
    void initView() {
        cameraView = findViewById(R.id.glsurfaceview_camera);

        publish = findViewById(R.id.publish);
        swCam = findViewById(R.id.swCam);
        swEnc = findViewById(R.id.swEnc);

        publish.setOnClickListener(this);
        swCam.setOnClickListener(this);
        swEnc.setOnClickListener(this);

        srsPublisher = new SrsPublisher(cameraView);
    }

    @Override
    void initData() {
        userId=getIntent().getIntExtra("userId",0);
        //编码状态回调
        srsPublisher.setEncodeHandler(new SrsEncodeHandler(this));
        srsPublisher.setRecordHandler(new SrsRecordHandler(this));
        //rtmp推流状态回调
        srsPublisher.setRtmpHandler(new RtmpHandler(this));
        //预览分辨率
        srsPublisher.setPreviewResolution(1280, 720);
        //推流分辨率
        srsPublisher.setOutputResolution(720, 1280);
        //传输率
        srsPublisher.setVideoHDMode();
        //开启美颜（其他滤镜效果在MagicFilterType中查看）
        srsPublisher.switchCameraFilter(MagicFilterType.BEAUTY);
        //打开摄像头，开始预览（未推流）
        srsPublisher.startCamera();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //开始/停止推流
            case R.id.publish:
                if (publish.getText().toString().contentEquals("开始")) {
                    getPresenter().getOpenLive(userId);
                } else if (publish.getText().toString().contentEquals("停止")) {
                    getPresenter().getCloseLive(userId);
                }
                break;
            //切换摄像头
            case R.id.swCam:
                srsPublisher.switchCameraFace((srsPublisher.getCamraId() + 1) % Camera.getNumberOfCameras());
                break;
            //切换编码方式
            case R.id.swEnc:
                if (swEnc.getText().toString().contentEquals("软编码")) {
                    srsPublisher.switchToSoftEncoder();
                    swEnc.setText("硬编码");
                } else if (swEnc.getText().toString().contentEquals("硬编码")) {
                    srsPublisher.switchToHardEncoder();
                    swEnc.setText("软编码");
                }
                break;
        }
    }

    @Override
    public void onSuccess(Object success,int flag) {
        switch (flag){
            case 1:
                openLiveBean= (OpenLiveBean) success;
                Toast.makeText(this, openLiveBean.getMessage(), Toast.LENGTH_SHORT).show();
                if (!TextUtils.isEmpty(openLiveBean.getStatus()) && openLiveBean.getStatus().contentEquals("0000")){
                    rtmpUrl=openLiveBean.getAddress();
                    close=false;
                    Log.e("PushStreamPresenter","rtmpUrl:"+rtmpUrl );
                    if (TextUtils.isEmpty(rtmpUrl)) {
                        Toast.makeText(getApplicationContext(), "地址不能为空！", Toast.LENGTH_SHORT).show();
                    }
                    srsPublisher.startPublish(rtmpUrl);
                    srsPublisher.startCamera();

                    if (swEnc.getText().toString().contentEquals("软编码")) {
                        Toast.makeText(getApplicationContext(), "当前使用硬编码", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "当前使用软编码", Toast.LENGTH_SHORT).show();
                    }
                    publish.setText("停止");
                    swEnc.setEnabled(false);
                }
                break;
            case 2:
                closeLiveBean= (CloseLiveBean) success;
                Toast.makeText(this, closeLiveBean.getMessage(), Toast.LENGTH_SHORT).show();
                if (!TextUtils.isEmpty(closeLiveBean.getStatus()) && closeLiveBean.getStatus().contentEquals("0000")){
                    srsPublisher.stopPublish();
                    srsPublisher.stopRecord();
                    publish.setText("开始");
                    swEnc.setEnabled(true);
                    if (close){
                        finish();
                    }
                }
                break;
        }
    }
    @Override
    public void onSuccess(Object success) { }

    public static void start(Context context, int userId) {
        Intent starter = new Intent(context, PushStreamActivity.class);
        starter.putExtra("userId", userId);
        context.startActivity(starter);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            close=true;
            getPresenter().getCloseLive(userId);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        srsPublisher.resumeRecord();
    }

    @Override
    protected void onPause() {
        super.onPause();
        srsPublisher.pauseRecord();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        srsPublisher.stopPublish();
        srsPublisher.stopRecord();
        getPresenter().detachView();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        srsPublisher.stopEncode();
        srsPublisher.stopRecord();
        srsPublisher.setScreenOrientation(newConfig.orientation);
        if (publish.getText().toString().contentEquals("停止")) {
            srsPublisher.startEncode();
        }
        srsPublisher.startCamera();
    }

    @Override
    public void onNetworkWeak() {
        Toast.makeText(getApplicationContext(), "网络信号弱", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNetworkResume() {

    }

    @Override
    public void onEncodeIllegalArgumentException(IllegalArgumentException e) {
        handleException(e);
    }

    @Override
    public void onRtmpConnecting(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRtmpConnected(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRtmpVideoStreaming() {

    }

    @Override
    public void onRtmpAudioStreaming() {

    }

    @Override
    public void onRtmpStopped() {
        Toast.makeText(getApplicationContext(), "已停止", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRtmpDisconnected() {
        Toast.makeText(getApplicationContext(), "未连接服务器", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRtmpVideoFpsChanged(double fps) {

    }

    @Override
    public void onRtmpVideoBitrateChanged(double bitrate) {

    }

    @Override
    public void onRtmpAudioBitrateChanged(double bitrate) {

    }

    @Override
    public void onRtmpSocketException(SocketException e) {
        handleException(e);
    }

    @Override
    public void onRtmpIOException(IOException e) {
        handleException(e);
    }

    @Override
    public void onRtmpIllegalArgumentException(IllegalArgumentException e) {
        handleException(e);
    }

    @Override
    public void onRtmpIllegalStateException(IllegalStateException e) {
        handleException(e);
    }

    @Override
    public void onRecordPause() {
        Toast.makeText(getApplicationContext(), "记录暂停", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRecordResume() {
        Toast.makeText(getApplicationContext(), "记录重新开始", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRecordStarted(String msg) {
        Toast.makeText(getApplicationContext(), "记录文件: " + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRecordFinished(String msg) {
        Toast.makeText(getApplicationContext(), "MP4 文件 保存: " + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRecordIllegalArgumentException(IllegalArgumentException e) {
        handleException(e);
    }

    @Override
    public void onRecordIOException(IOException e) {
        handleException(e);
    }

    private void handleException(Exception e) {
        try {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            srsPublisher.stopPublish();
            srsPublisher.stopRecord();
            publish.setText("开始");
        } catch (Exception e1) {
            Log.e(TAG, e1.toString());
        }
    }

    @Override
    PushStreamPresenter initPresenter() {
        return new PushStreamPresenter();
    }

    @Override
    int setChildContentView() {
        return R.layout.activity_push_stream;
    }
}
