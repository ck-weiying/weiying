package com.example.weiying.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weiying.R;
import com.example.weiying.model.bean.CommentBean;
import com.example.weiying.utils.FrescoUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * author:Created by WangZhiQiang on 2018/7/16.
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentHolder>{
    Context context;
    List<CommentBean.RetBean.ListBean> list;

    public CommentAdapter(Context context, List<CommentBean.RetBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comment_item, parent, false);
        CommentHolder commentHolder = new CommentHolder(view);
        return commentHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentHolder holder, int position) {
        if (!TextUtils.isEmpty(list.get(position).getUserPic())){
            FrescoUtil.setYuanQuan(list.get(position).getUserPic(),holder.userIcon,0,0);
        }
        holder.userName.setText(list.get(position).getUserName() == null ? "用户" : list.get(position).getPhoneNumber());
        holder.createTime.setText(list.get(position).getTime());
        holder.comment.setText(list.get(position).getMsg());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class CommentHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView userIcon;
        TextView userName;
        TextView createTime;
        TextView comment;

        CommentHolder(View view) {
            super(view);
            userIcon=view.findViewById(R.id.user_icon);
            userName=view.findViewById(R.id.userName);
            createTime=view.findViewById(R.id.createTime);
            comment=view.findViewById(R.id.comment);
        }

    }
}
