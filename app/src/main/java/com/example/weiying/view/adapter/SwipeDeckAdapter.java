package com.example.weiying.view.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.weiying.R;
import com.example.weiying.model.bean.FeaturedBean;
import com.example.weiying.utils.ImageLoader;
import com.example.weiying.view.activity.DetailsActivity;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

/**
 * author:Created by WangZhiQiang on 2018/7/13.
 */
public class SwipeDeckAdapter extends BaseAdapter {
    private List<FeaturedBean.RetBean.ListBean.ChildListBean> data;
    private Context context;
    private LayoutInflater inflater;

    public SwipeDeckAdapter(List<FeaturedBean.RetBean.ListBean.ChildListBean> data, Context context) {
        this.data = data;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.card_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ImageLoader.load(context, data.get(position).getPic(), holder.offerImage);
        String intro = "\t\t\t" + data.get(position).getDescription();
        holder.tvIntroduction.setText(intro);
        holder.tv_title.setText(data.get(position).getTitle());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "cardView", Toast.LENGTH_SHORT).show();
                DetailsActivity.start(context,data.get(position).getDataId());
            }
        });
        return convertView;
    }

    static class ViewHolder {
        RoundedImageView offerImage;
        TextView tvIntroduction;
        CardView cardView;
        TextView tv_title;

        ViewHolder(View view) {
            offerImage=view.findViewById(R.id.offer_image);
            tvIntroduction=view.findViewById(R.id.tv_introduction);
            cardView=view.findViewById(R.id.card_view);
            tv_title=view.findViewById(R.id.tv_title);
        }
    }
}
