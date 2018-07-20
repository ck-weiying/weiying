package com.example.weiying.view.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.weiying.R;
import com.example.weiying.application.MyApp;
import com.example.weiying.model.bean.LiveBean;
import com.example.weiying.presenter.LivePresenter;
import com.example.weiying.view.activity.LoginActivity;
import com.example.weiying.view.activity.PlayStreamActivity;
import com.example.weiying.view.activity.PushStreamActivity;
import com.example.weiying.view.adapter.LiveAdapter;
import com.example.weiying.view.interfaces.ILiveView;

import java.util.ArrayList;
import java.util.List;

/**
 * author:Created by WangZhiQiang on 2018/7/17.
 */
public class LiveFragment extends BaseFragment<LivePresenter> implements ILiveView,View.OnClickListener{
    private RecyclerView live_rv;
//    private Button push_stream_btn;
    private Button zhu;
    private boolean flag;

    private LiveBean liveBean=new LiveBean();
    private LiveAdapter liveAdapter;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    @Override
    void initView(View view) {
        live_rv = view.findViewById(R.id.live_rv);
//        push_stream_btn = view.findViewById(R.id.push_stream_btn);
        zhu = view.findViewById(R.id.zhu);
//        push_stream_btn.setOnClickListener(this);
        zhu.setOnClickListener(this);
    }

    @Override
    void initData() {
        liveAdapter = new LiveAdapter(getActivity());
        live_rv.setAdapter(liveAdapter);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        live_rv.setLayoutManager(staggeredGridLayoutManager);
        requestPermissions();
        getPresenter().getDataFromServer();
    }

    @Override
    public void onSuccess(Object success) {
        liveBean= (LiveBean) success;
        if (!TextUtils.isEmpty(liveBean.getStatus()) && liveBean.getStatus().contentEquals("0000")){
            liveBean.getResult().add(0,new LiveBean.ResultBean());
            liveAdapter.setData(liveBean.getResult());
            liveAdapter.getCallBackView(new LiveAdapter.CallBackView() {
                @Override
                public void CallBack(View view, final int position, final String address) {
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (position==0){
                                if (flag){
                                    int userId = MyApp.sharedPreferences.getInt("userId", 0);
                                    if (userId==0){
                                        Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                                        LoginActivity.start(getActivity());
                                    }else {
                                        PushStreamActivity.start(getActivity(),userId);
                                    }
                                }
                            }else {
                                PlayStreamActivity.start(getActivity(),address);
                            }
                        }
                    });
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zhu:
                MyApp.editor.clear().commit();
                break;
        }
    }

    @Override
    LivePresenter initPresenter() {
        return new LivePresenter();
    }

    @Override
    int setChildContentView() {
        return R.layout.fragment_live;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            requestPermissions();
            getPresenter().getDataFromServer();
        }
    }

    /**
     * 当有多个权限需要申请的时候
     * 这里以打电话和SD卡读写权限为例
     */
    private void requestPermissions(){
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.INTERNET);
        }
        if (ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.CAMERA);
        }
        if (ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.RECORD_AUDIO);
        }
        if (ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_NETWORK_STATE);
        }
        if (ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.WAKE_LOCK) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WAKE_LOCK);
        }
        if (!permissionList.isEmpty()){  //申请的集合不为空时，表示有需要申请的权限
            ActivityCompat.requestPermissions(getActivity(),permissionList.toArray(new String[permissionList.size()]),1);
        }else { //所有的权限都已经授权过了
            flag=true;
        }
    }

    /**
     * 权限申请返回结果
     * @param requestCode 请求码
     * @param permissions 权限数组
     * @param grantResults  申请结果数组，里面都是int类型的数
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0){ //安全写法，如果小于0，肯定会出错了
                    for (int i = 0; i < grantResults.length; i++) {
                        int grantResult = grantResults[i];
                        if (grantResult == PackageManager.PERMISSION_DENIED){ //这个是权限拒绝
                            String s = permissions[i];
                            Toast.makeText(getActivity(),s+"权限被拒绝了",Toast.LENGTH_SHORT).show();
                        }else{ //授权成功了
                            flag=true;
                            getPresenter().getDataFromServer();
                        }
                    }
                }
                break;
            default:
                break;
        }
    }
}
