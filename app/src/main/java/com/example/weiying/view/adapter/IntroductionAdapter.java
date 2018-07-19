package com.example.weiying.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.weiying.R;
import com.example.weiying.model.bean.DetailsBean;
import com.example.weiying.view.activity.DetailsActivity;

import java.util.List;

/**
 * author:Created by WangZhiQiang on 2018/7/16.
 */
public class IntroductionAdapter extends RecyclerView.Adapter<IntroductionAdapter.RecommendHolder>{
    private Context context;
    private List<DetailsBean.RetBean.ListBean.ChildListBean> list;

    public IntroductionAdapter(Context context, List<DetailsBean.RetBean.ListBean.ChildListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecommendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.introduction_item, parent, false);
        RecommendHolder recommendHolder = new RecommendHolder(view);
        return recommendHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendHolder holder, final int position) {
        holder.recommend_tv.setText(list.get(position).getTitle());
        Glide.with(context).load(list.get(position).getPic()).into(holder.recommend_iv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailsActivity detailsActivity=(DetailsActivity)context;
                DetailsActivity.start(context,list.get(position).getDataId());
                detailsActivity.finish();
            }
        });
    }
    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class RecommendHolder extends RecyclerView.ViewHolder {
        ImageView recommend_iv;
        TextView recommend_tv;

        RecommendHolder(View view) {
            super(view);
            recommend_iv=view.findViewById(R.id.recommend_iv);
            recommend_tv=view.findViewById(R.id.recommend_tv);
        }
    }
}
