package com.example.weiying.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weiying.R;
import com.example.weiying.model.bean.FeaturedBean;
import com.example.weiying.utils.FrescoUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * author:Created by WangZhiQiang on 2018/7/12.
 */
public class FeaturedSatelliteAdapter extends RecyclerView.Adapter<FeaturedSatelliteAdapter.FeaturedSatelliteHolder> {
    private Context context;
    private List<FeaturedBean.RetBean.ListBean.ChildListBean> list=new ArrayList<>();

    public FeaturedSatelliteAdapter(Context context,List<FeaturedBean.RetBean.ListBean.ChildListBean> list) {
        this.context = context;
        this.list=list;
        Log.e("ChildListBean", list.toString());
    }

    @NonNull
    @Override
    public FeaturedSatelliteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.featured_satellite_item_layout, parent, false);
        FeaturedSatelliteHolder featuredSatelliteHolder = new FeaturedSatelliteHolder(view);
        return featuredSatelliteHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedSatelliteHolder holder, int position) {
        FrescoUtil.setJianJin(list.get(position).getPic(),holder.featured_satellite_item_sdv);
        holder.featured_satellite_item_tv.setText(list.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                context.startActivity(new Intent(context, VideoActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class FeaturedSatelliteHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView featured_satellite_item_sdv;
        TextView featured_satellite_item_tv;

        public FeaturedSatelliteHolder(View itemView) {
            super(itemView);
            featured_satellite_item_sdv=itemView.findViewById(R.id.featured_satellite_item_sdv);
            featured_satellite_item_tv=itemView.findViewById(R.id.featured_satellite_item_tv);
        }
    }
}
