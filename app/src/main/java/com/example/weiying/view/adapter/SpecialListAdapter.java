package com.example.weiying.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weiying.R;
import com.example.weiying.model.bean.SpecialListBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class SpecialListAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<SpecialListBean.RetBean.ListBean> list=new ArrayList<>();

    public SpecialListAdapter(Context context,List<SpecialListBean.RetBean.ListBean> list) {
        this.context=context;
        this.list=list;
    }
    //清除数据
    public void setDataClear(List<SpecialListBean.RetBean.ListBean> list){
        this.list.clear();
        if (list!=null){
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }
    //获取数据
    public void setData(List<SpecialListBean.RetBean.ListBean> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.special_recycler_item,null);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder mHolder=(MyViewHolder)holder;
//        String pic = list.get(position).getPic();
//        String pic = list.get(position).getChildList().get(0).getPic();
//        mHolder.special_recycler_iv.setImageURI(pic);
//        mHolder.special_recycler_title.setText(list.get(position).getTitle());
          mHolder.special_recycler_title.setText(list.get(position).getTitle());
        String pic = list.get(position).getPic();
        Uri uri = Uri.parse(pic);
        mHolder.special_recycler_iv.setImageURI(uri);
        mHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, SpecialListActivity.class);
//                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list==null ? 0:list.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private final SimpleDraweeView special_recycler_iv;
        private final TextView special_recycler_title;

        public MyViewHolder(View view) {
            super(view);
            special_recycler_iv = view.findViewById(R.id.special_recycler_iv);
            special_recycler_title = view.findViewById(R.id.special_recycler_title);
        }
    }
}
