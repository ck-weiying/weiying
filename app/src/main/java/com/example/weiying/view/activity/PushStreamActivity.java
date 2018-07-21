package com.example.weiying.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
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

    private Button push_stream_onoff_btn;
    private Button push_stream_camera_btn;
    private Button push_stream_encode_btn;
    private Button push_stream_record_btn;

    private String recPath = Environment.getExternalStorageDirectory().getPath() + "/test.mp4";
    private String rtmpUrl;
    private int userId;
    private boolean close;
    private OpenLiveBean openLiveBean=new OpenLiveBean();
    private CloseLiveBean closeLiveBean=new CloseLiveBean();

    @Override
    void initView() {
        cameraView = findViewById(R.id.glsurfaceview_camera);

        push_stream_onoff_btn = findViewById(R.id.push_stream_onoff_btn);
        push_stream_camera_btn = findViewById(R.id.push_stream_camera_btn);
        push_stream_encode_btn = findViewById(R.id.push_stream_encode_btn);
        push_stream_record_btn = findViewById(R.id.push_stream_record_btn);

        push_stream_onoff_btn.setOnClickListener(this);
        push_stream_camera_btn.setOnClickListener(this);
        push_stream_encode_btn.setOnClickListener(this);
        push_stream_record_btn.setOnClickListener(this);
        //响应屏幕翻转事件
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
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
            case R.id.push_stream_onoff_btn:
                if (push_stream_onoff_btn.getText().toString().contentEquals("开始")) {
                    getPresenter().getOpenLive(userId);
                } else if (push_stream_onoff_btn.getText().toString().contentEquals("停止")) {
                    getPresenter().getCloseLive(userId);
                }
                break;
            //切换摄像头
            case R.id.push_stream_camera_btn:
                srsPublisher.switchCameraFace((srsPublisher.getCamraId() + 1) % Camera.getNumberOfCameras());
                break;
            //切换编码方式
            case R.id.push_stream_encode_btn:
                if (push_stream_encode_btn.getText().toString().contentEquals("软编码")) {
                    srsPublisher.switchToSoftEncoder();
                    push_stream_encode_btn.setText("硬编码");
                } else if (push_stream_encode_btn.getText().toString().contentEquals("硬编码")) {
                    srsPublisher.switchToHardEncoder();
                    push_stream_encode_btn.setText("软编码");
                }
                break;
            //开始记录
            case R.id.push_stream_record_btn:
                if (push_stream_record_btn.getText().toString().contentEquals("录制")) {
                    if (srsPublisher.startRecord(recPath)) {
                        push_stream_record_btn.setText("暂停");
                    }
                } else if (push_stream_record_btn.getText().toString().contentEquals("暂停")) {
                    srsPublisher.pauseRecord();
                    push_stream_record_btn.setText("继续");
                } else if (push_stream_record_btn.getText().toString().contentEquals("继续")) {
                    srsPublisher.resumeRecord();
                    push_stream_record_btn.setText("暂停");
                }
                break;
        }
    }

    //设置菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    //菜单操作监听
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //处理动作栏项目点击这里。操作栏将自动处理Home/Up按钮上的点击击，太长了当您在AndroidManifest.xml中指定父活动时。
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        } else {
            switch (id) {
                case R.id.cool_filter:
                    srsPublisher.switchCameraFilter(MagicFilterType.COOL);
                    break;
                case R.id.beauty_filter:
                    srsPublisher.switchCameraFilter(MagicFilterType.BEAUTY);
                    break;
                case R.id.early_bird_filter:
                    srsPublisher.switchCameraFilter(MagicFilterType.EARLYBIRD);
                    break;
                case R.id.evergreen_filter:
                    srsPublisher.switchCameraFilter(MagicFilterType.EVERGREEN);
                    break;
                case R.id.n1977_filter:
                    srsPublisher.switchCameraFilter(MagicFilterType.N1977);
                    break;
                case R.id.nostalgia_filter:
                    srsPublisher.switchCameraFilter(MagicFilterType.NOSTALGIA);
                    break;
                case R.id.romance_filter:
                    srsPublisher.switchCameraFilter(MagicFilterType.ROMANCE);
                    break;
                case R.id.sunrise_filter:
                    srsPublisher.switchCameraFilter(MagicFilterType.SUNRISE);
                    break;
                case R.id.sunset_filter:
                    srsPublisher.switchCameraFilter(MagicFilterType.SUNSET);
                    break;
                case R.id.tender_filter:
                    srsPublisher.switchCameraFilter(MagicFilterType.TENDER);
                    break;
                case R.id.toast_filter:
                    srsPublisher.switchCameraFilter(MagicFilterType.TOASTER2);
                    break;
                case R.id.valencia_filter:
                    srsPublisher.switchCameraFilter(MagicFilterType.VALENCIA);
                    break;
                case R.id.walden_filter:
                    srsPublisher.switchCameraFilter(MagicFilterType.WALDEN);
                    break;
                case R.id.warm_filter:
                    srsPublisher.switchCameraFilter(MagicFilterType.WARM);
                    break;
                case R.id.original_filter:
                default:
                    srsPublisher.switchCameraFilter(MagicFilterType.NONE);
                    break;
            }
        }
        setTitle(item.getTitle());

        return super.onOptionsItemSelected(item);
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

                    if (push_stream_encode_btn.getText().toString().contentEquals("软编码")) {
                        Toast.makeText(getApplicationContext(), "当前使用硬编码", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "当前使用软编码", Toast.LENGTH_SHORT).show();
                    }
                    push_stream_onoff_btn.setText("停止");
                    push_stream_encode_btn.setEnabled(false);
                }
                break;
            case 2:
                closeLiveBean= (CloseLiveBean) success;
                Toast.makeText(this, closeLiveBean.getMessage(), Toast.LENGTH_SHORT).show();
                if (!TextUtils.isEmpty(closeLiveBean.getStatus()) && closeLiveBean.getStatus().contentEquals("0000")){
                    srsPublisher.stopPublish();
                    srsPublisher.stopRecord();
                    push_stream_onoff_btn.setText("开始");
                    push_stream_encode_btn.setEnabled(true);
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
        push_stream_record_btn.setText("录制");
        srsPublisher.setScreenOrientation(newConfig.orientation);
        if (push_stream_onoff_btn.getText().toString().contentEquals("停止")) {
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
        Toast.makeText(getApplicationContext(), "网络重连", Toast.LENGTH_SHORT).show();
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
        if (!close){
            Toast.makeText(getApplicationContext(), "未连接服务器", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRtmpVideoFpsChanged(double fps) {
        Log.i(TAG, String.format("Output Fps: %f", fps));
    }

    @Override
    public void onRtmpVideoBitrateChanged(double bitrate) {
        int rate = (int) bitrate;
        if (rate / 1000 > 0) {
            Log.i(TAG, String.format("Video bitrate: %f kbps", bitrate / 1000));
        } else {
            Log.i(TAG, String.format("Video bitrate: %d bps", rate));
        }
    }

    @Override
    public void onRtmpAudioBitrateChanged(double bitrate) {
        int rate = (int) bitrate;
        if (rate / 1000 > 0) {
            Log.i(TAG, String.format("Audio bitrate: %f kbps", bitrate / 1000));
        } else {
            Log.i(TAG, String.format("Audio bitrate: %d bps", rate));
        }
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
        Toast.makeText(getApplicationContext(), "录制暂停", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRecordResume() {
        Toast.makeText(getApplicationContext(), "录制重新开始", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRecordStarted(String msg) {
        Toast.makeText(getApplicationContext(), "录制文件: " + msg, Toast.LENGTH_SHORT).show();
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
            push_stream_onoff_btn.setText("开始");
            push_stream_record_btn.setText("录制");
            push_stream_encode_btn.setEnabled(true);
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
