package com.example.weiying.view.activity;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weiying.R;
import com.example.weiying.presenter.MainPresenter;

/**
 * Created by dell on 2018/7/11.
 */

public class DetailsActivity extends BaseActivity<MainPresenter> {

    private ImageView delete;
    private TextView toback;
    private GridView gv;

    @Override
    void initView() {
        delete = findViewById(R.id.detalis_delete);
        toback = findViewById(R.id.Toback);
        gv = findViewById(R.id.gv);
        delete.setColorFilter(R.color.color_TiShi_bw);

        toback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    void initData() {

    }

    @Override
    MainPresenter setBasePresenter() {
        return null;
    }

    @Override
    int setChildContentView() {
        return R.layout.details_layout;
    }
    class gridAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            return null;
        }
    }
}
