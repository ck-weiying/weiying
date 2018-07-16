package com.example.weiying.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weiying.R;
import com.example.weiying.model.bean.SpecialBean;
import com.example.weiying.view.activity.SpecialListActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class SpecialAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<SpecialBean.RetBean.ListBean> list=new ArrayList<>();
    private String catalogId;
    public SpecialAdapter(Context context) {
        this.context=context;
    }
    //清除数据
    public void getDataClear(List<SpecialBean.RetBean.ListBean> list){
        this.list.clear();
        if (list!=null){
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }
    //获取数据
    public void getData(List<SpecialBean.RetBean.ListBean> list){
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder mHolder=(MyViewHolder)holder;
        String pic = list.get(position).getChildList().get(0).getPic();
        if(!pic.equals("")){
            Uri uri = Uri.parse(pic);
            mHolder.special_iv.setImageURI(uri); 
        }
        String title = list.get(position).getTitle();
        if(title!=null){
        mHolder.special_text.setText(title);   
        }
        
        mHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String moreURL = list.get(position).getMoreURL();
                if(!moreURL.equals("")){
                    String[] split = moreURL.split("=");
                    for (int i = 0; i <split.length ; i++) {
                        String a = split[1];
                        String[] b = a.split("&");
                        for (int j = 0; j < b.length; j++) {
                            catalogId = b[0];
                        }
                    }   
                }
                Intent intent = new Intent(context, SpecialListActivity.class).putExtra("title",list.get(position).getTitle()).putExtra("catalogId",catalogId);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private final SimpleDraweeView special_iv;
        private final TextView special_text;

        public MyViewHolder(View view) {
            super(view);
            special_iv = view.findViewById(R.id.special_iv);
            special_text = view.findViewById(R.id.special_text);
        }
    }
}
