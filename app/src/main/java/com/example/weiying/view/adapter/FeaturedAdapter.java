package com.example.weiying.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weiying.R;
import com.example.weiying.model.bean.FeaturedBean;

import java.util.ArrayList;
import java.util.List;

/**
 * author:Created by WangZhiQiang on 2018/7/12.
 */
public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder> {
    private Context context;
    private List<FeaturedBean.RetBean.ListBean> list=new ArrayList<>();
    private RecyclerView.RecycledViewPool viewPool;

    public FeaturedAdapter(Context context) {
        this.context = context;
        viewPool = new RecyclerView.RecycledViewPool();
    }

    public void setData(List<FeaturedBean.RetBean.ListBean> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.featured_item_layout, parent, false);
        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(view);
        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {
        holder.featured_item_tv.setText(list.get(position).getTitle());
        holder.featured_item_rv.setRecycledViewPool(viewPool);
        holder.featured_item_rv.setLayoutManager(new LinearLayoutManager(context));
        holder.featured_item_rv.setAdapter(new FeaturedSatelliteAdapter(context,list.get(position).getChildList()));
        holder.featured_item_rv.setNestedScrollingEnabled(false);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class FeaturedViewHolder extends RecyclerView.ViewHolder {

        TextView featured_item_tv;
        RecyclerView featured_item_rv;

        public FeaturedViewHolder(View itemView) {
            super(itemView);
            featured_item_tv=itemView.findViewById(R.id.featured_item_tv);
            featured_item_rv=itemView.findViewById(R.id.featured_item_rv);
        }
    }
}
