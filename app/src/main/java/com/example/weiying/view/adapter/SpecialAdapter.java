package com.example.weiying.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weiying.R;
import com.example.weiying.model.bean.SpecialBean;
import com.example.weiying.view.activity.SpecialListActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class SpecialAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<SpecialBean.RetBean.ListBean.ChildListBean> list=new ArrayList<>();
    public SpecialAdapter(Context context, List<SpecialBean.RetBean.ListBean.ChildListBean> list) {
        this.context=context;
        this.list=list;
    }
    //清除数据
    public void setDataClear(List<SpecialBean.RetBean.ListBean.ChildListBean> list){
        this.list.clear();
        if (list!=null){
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }
    //获取数据
    public void setData(List<SpecialBean.RetBean.ListBean.ChildListBean> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.special_recycler,null);
        return new  MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder mHolder=(MyViewHolder)holder;
        String pic = list.get(position).getPic();
        mHolder.special_iv.setImageURI(pic);
        mHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SpecialListActivity.class);
                context.startActivity(intent);
            }
        });
        
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private final SimpleDraweeView special_iv;
        public MyViewHolder(View view) {
            super(view);
            special_iv = view.findViewById(R.id.special_iv);
        }
    }
}
