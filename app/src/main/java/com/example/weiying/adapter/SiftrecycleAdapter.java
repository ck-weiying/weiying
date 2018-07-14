package com.example.weiying.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weiying.Bean.SiftFragBean;
import com.example.weiying.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dell on 2018/7/10.
 */

public class SiftrecycleAdapter extends RecyclerView.Adapter<SiftrecycleAdapter.SiftHolder> {
    public SiftrecycleAdapter(Context context, List<SiftFragBean.RetBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    private Context context;
    private List<SiftFragBean.RetBean.ListBean> list;

    @NonNull
    @Override
    public SiftHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sift_adapter_layout, parent, false);
        SiftHolder siftHolder = new SiftHolder(view);
        return siftHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SiftHolder holder, int position) {
        holder.siftContentitemImg.setImageURI(list.get(position).getChildList().get(0).getPic());
        holder.siftContentTitle.setText(list.get(position).getChildList().get(0).getTitle()
                ==null?list.get(position).getChildList().get(1).getTitle():list.get(position).getChildList().get(0).getTitle());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class SiftHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.sift_contentitem_img)
        SimpleDraweeView siftContentitemImg;
        @BindView(R.id.sift_content_title)
        TextView siftContentTitle;
        public SiftHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
