package com.example.weiying.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weiying.R;
import com.example.weiying.model.bean.LiveBean;
import com.example.weiying.utils.FrescoUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * author:Created by WangZhiQiang on 2018/7/16.
 */
public class LiveAdapter extends RecyclerView.Adapter<LiveAdapter.LiveHolder>{
    private Context context;
    private List<LiveBean.ResultBean> list=new ArrayList<>();
    private CallBackView callBackView;

    public LiveAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<LiveBean.ResultBean> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void getCallBackView(CallBackView callBackView){
        this.callBackView=callBackView;
    }

    @NonNull
    @Override
    public LiveHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_live_item, parent, false);
        LiveHolder liveHolder = new LiveHolder(view);
        return liveHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LiveHolder holder, int position) {
        String urlIco="http://7xi8d6.com1.z0.glb.clouddn.com/20180102083655_3t4ytm_Screenshot.jpeg";
        if (position==0){
            holder.live_item_sdv.setImageResource(R.mipmap.live);
            holder.live_item_tv.setText("开始直播");
        }else {
            FrescoUtil.setControllerListener(holder.live_item_sdv,urlIco, (int) context.getResources().getDimension(R.dimen.dp_170));
            holder.live_item_tv.setText(list.get(position).getNickName());
        }
        if (callBackView!=null){
            callBackView.CallBack(holder.itemView,position,list.get(position).getAddress());
        }
    }
    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class LiveHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView live_item_sdv;
        TextView live_item_tv;

        LiveHolder(View view) {
            super(view);
            live_item_sdv=view.findViewById(R.id.live_item_sdv);
            live_item_tv=view.findViewById(R.id.live_item_tv);
        }
    }

    public interface CallBackView {
        void CallBack(View view,int position, String address);
    }
}
