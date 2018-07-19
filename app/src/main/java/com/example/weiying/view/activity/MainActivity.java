package com.example.weiying.view.activity;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.color.ColorChooserDialog;
import com.example.weiying.R;
import com.example.weiying.application.Theme;
import com.example.weiying.utils.ColorUiUtil;
import com.example.weiying.utils.PreUtils;
import com.example.weiying.utils.StringUtils;
import com.example.weiying.utils.ThemeUtil;
import com.example.weiying.utils.ThemeUtils;
import com.example.weiying.view.customview.ResideLayout;
import com.example.weiying.view.fragment.DiscoveryFragment;
import com.example.weiying.view.fragment.FeaturedFragment;
import com.example.weiying.view.fragment.FeaturesFragment;
import com.example.weiying.view.fragment.LiveFragment;
import com.example.weiying.view.fragment.MineFragment;
import com.hjm.bottomtabbar.BottomTabBar;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;

import org.greenrobot.eventbus.EventBus;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,ColorChooserDialog.ColorCallback{

    private ResideLayout resideLayout;
    private TextView menu_mine_collection;
    private TextView menu_mine_download;
    private TextView menu_benefits;
    private TextView menu_share;
    private TextView menu_feedback;
    private TextView menu_settings;
    private TextView menu_about;
    private TextView menu_theme;
    private BottomTabBar bottom_tab_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onPreCreate();
        setContentView(R.layout.activity_main);
        initView();
        float img_size_width = getResources().getDimension(R.dimen.dp_22);
        float img_size_height = getResources().getDimension(R.dimen.dp_24);
        float font_size = getResources().getDimension(R.dimen.dp_10);
        bottom_tab_bar.init(getSupportFragmentManager(),720, 1280)
                .setImgSize(img_size_width,img_size_height)
                .setFontSize(font_size)
                .addTabItem("精选", R.mipmap.found_select,R.mipmap.found, FeaturedFragment.class)
                .addTabItem("专题",R.mipmap.special_select,R.mipmap.special,FeaturesFragment.class)
                .addTabItem("直播",R.mipmap.fancy_select,R.mipmap.fancy,LiveFragment.class)
                .addTabItem("发现",R.mipmap.fancy_select,R.mipmap.fancy,DiscoveryFragment.class)
                .addTabItem("我的",R.mipmap.my_select,R.mipmap.my,MineFragment.class)
                .setTabBarBackgroundResource(R.mipmap.bottom_bg)
                .isShowDivider(false);
    }

    private void initView() {
        resideLayout = findViewById(R.id.resideLayout);
        menu_mine_collection = findViewById(R.id.menu_mine_collection);
        menu_mine_download = findViewById(R.id.menu_mine_download);
        menu_benefits = findViewById(R.id.menu_benefits);
        menu_share = findViewById(R.id.menu_share);
        menu_feedback = findViewById(R.id.menu_feedback);
        menu_settings = findViewById(R.id.menu_settings);
        menu_about = findViewById(R.id.menu_about);
        menu_theme = findViewById(R.id.menu_theme);
        bottom_tab_bar = findViewById(R.id.bottom_tab_bar);

        menu_mine_collection.setOnClickListener(this);
        menu_mine_download.setOnClickListener(this);
        menu_benefits.setOnClickListener(this);
        menu_share.setOnClickListener(this);
        menu_feedback.setOnClickListener(this);
        menu_settings.setOnClickListener(this);
        menu_about.setOnClickListener(this);
        menu_theme.setOnClickListener(this);

//        EventBus.getDefault().register(this);

        int icon_size = (int) getResources().getDimension(R.dimen.dp_16);
        int icon_padding = (int) getResources().getDimension(R.dimen.dp_10);
        StringUtils.setIconDrawable(this, menu_mine_collection, MaterialDesignIconic.Icon.gmi_collection_bookmark, icon_size, icon_padding);
        StringUtils.setIconDrawable(this, menu_mine_download, MaterialDesignIconic.Icon.gmi_download, icon_size, icon_padding);
        StringUtils.setIconDrawable(this, menu_benefits, MaterialDesignIconic.Icon.gmi_mood, icon_size, icon_padding);
        StringUtils.setIconDrawable(this, menu_share, MaterialDesignIconic.Icon.gmi_share, icon_size, icon_padding);
        StringUtils.setIconDrawable(this, menu_feedback, MaterialDesignIconic.Icon.gmi_android, icon_size, icon_padding);
        StringUtils.setIconDrawable(this, menu_settings, MaterialDesignIconic.Icon.gmi_settings, icon_size, icon_padding);
        StringUtils.setIconDrawable(this, menu_about, MaterialDesignIconic.Icon.gmi_account, icon_size, icon_padding);
        StringUtils.setIconDrawable(this, menu_theme, MaterialDesignIconic.Icon.gmi_palette, icon_size, icon_padding);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_mine_collection:
//                startActivity(new Intent(MainActivity.this, CollectionActivity.class));
                Toast.makeText(this, "我的收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_mine_download:
                Toast.makeText(this, "敬请期待", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_benefits:
//                mContext.startActivity(new Intent(MainActivity.this, WelfareActivity.class));
                Toast.makeText(this, "福利", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_share:
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.setting_recommend_content));
                shareIntent.setType("text/plain");

                //设置分享列表的标题，并且每次都显示分享列表
                startActivity(Intent.createChooser(shareIntent, "分享到"));
                break;
            case R.id.menu_feedback:
                // 以对话框的形式弹出
                Toast.makeText(this, "建议反馈", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_settings:
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
                break;
            case R.id.menu_about:
                new MaterialDialog.Builder(this)
                        .title(R.string.about)
                        .icon(new IconicsDrawable(this)
                                .color(ThemeUtils.getThemeColor(this, R.attr.colorPrimary))
                                .icon(MaterialDesignIconic.Icon.gmi_account)
                                .sizeDp(20))
                        .content(R.string.about_me)
                        .positiveText(R.string.close)
                        .show();
                break;
            case R.id.menu_theme:
                new ColorChooserDialog.Builder(this,R.string.theme)
                        .customColors(R.array.colors, null)
                        .doneButton(R.string.done)
                        .cancelButton(R.string.cancel)
                        .allowUserColorInput(false)
                        .allowUserColorInputAlpha(false)
                        .show();
                break;
        }
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }
    //记录用户首次点击返回键的时间
    private long firstTime = 0;
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (resideLayout.isOpen()) {
                    resideLayout.closePane();
                    return true;
                }
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {
                    Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;
                    return true;
                } else {
                    System.exit(0);
                }
                break;
            default:
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void onColorSelection(@NonNull ColorChooserDialog dialog, int selectedColor) {
        ThemeUtil.onColorSelection(this, dialog, selectedColor);
//        EventBus.getDefault().postSticky();

        final View rootView = getWindow().getDecorView();
        rootView.setDrawingCacheEnabled(true);
        rootView.buildDrawingCache(true);

        final Bitmap localBitmap = Bitmap.createBitmap(rootView.getDrawingCache());
        rootView.setDrawingCacheEnabled(false);
        if (null != localBitmap && rootView instanceof ViewGroup) {
            final View tmpView = new View(getApplicationContext());
            tmpView.setBackgroundDrawable(new BitmapDrawable(getResources(), localBitmap));
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            ((ViewGroup) rootView).addView(tmpView, params);
            tmpView.animate().alpha(0).setDuration(400).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    ColorUiUtil.changeTheme(rootView, getTheme());
                    System.gc();
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    ((ViewGroup) rootView).removeView(tmpView);
                    localBitmap.recycle();
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            }).start();
        }
    }

    private void onPreCreate() {
        Theme theme = PreUtils.getCurrentTheme(this);
        switch (theme) {
            case Blue:
                setTheme(R.style.BlueTheme);
                break;
            case Red:
                setTheme(R.style.RedTheme);
                break;
            case Brown:
                setTheme(R.style.BrownTheme);
                break;
            case Green:
                setTheme(R.style.GreenTheme);
                break;
            case Purple:
                setTheme(R.style.PurpleTheme);
                break;
            case Teal:
                setTheme(R.style.TealTheme);
                break;
            case Pink:
                setTheme(R.style.PinkTheme);
                break;
            case DeepPurple:
                setTheme(R.style.DeepPurpleTheme);
                break;
            case Orange:
                setTheme(R.style.OrangeTheme);
                break;
            case Indigo:
                setTheme(R.style.IndigoTheme);
                break;
            case LightGreen:
                setTheme(R.style.LightGreenTheme);
                break;
            case Lime:
                setTheme(R.style.LimeTheme);
                break;
            case DeepOrange:
                setTheme(R.style.DeepOrangeTheme);
                break;
            case Cyan:
                setTheme(R.style.CyanTheme);
                break;
            case BlueGrey:
                setTheme(R.style.BlueGreyTheme);
                break;
            case Black:
                setTheme(R.style.BlackTheme);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
