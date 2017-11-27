package com.hxe.platform.activity;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.yellowsmalltwo.R;
import com.hxe.platform.base.BaseActivity;
import com.hxe.platform.fragment.DuanZiFragment;
import com.hxe.platform.fragment.LeftFragment;
import com.hxe.platform.fragment.ShiPinFragment;
import com.hxe.platform.fragment.TuiJianFragment;
import com.kson.slidingmenu.SlidingMenu;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_tuijian;
    private LinearLayout ll_duanzi;
    private LinearLayout ll_shipin;
    private ImageView iv_wode;
    private SlidingMenu menu;

    @Override
    public boolean Immersion() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        //加载控件
        initview();
        //点击事件
        initclick();

        getSupportFragmentManager().beginTransaction().replace(R.id.fl_main_fragment, new TuiJianFragment()).commit();

        //实例化Slidingenu
        menu = new SlidingMenu(this);
        //左布局控件
        menu.setMenu(R.layout.left_menu);
        //替换左布局
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_left_menu, new LeftFragment()).commit();
        //设置左侧拉权限
        menu.setMode(SlidingMenu.LEFT);
        //设置滑动范围
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        //设置主布局滑动后剩余宽度
        menu.setBehindOffsetRes(R.dimen.margin);
        //使SlidingMenu依附在Activity上
        menu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);


    }

    //点击事件
    private void initclick() {

        ll_tuijian.setOnClickListener(this);
        ll_duanzi.setOnClickListener(this);
        ll_shipin.setOnClickListener(this);
        iv_wode.setOnClickListener(this);

    }

    private void initview() {
        //下方控件（推荐、段子、视频）
        ll_tuijian = findViewById(R.id.ll_tuijian);
        ll_duanzi = findViewById(R.id.ll_duanzi);
        ll_shipin = findViewById(R.id.ll_shipin);
        iv_wode = findViewById(R.id.iv_wode);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_tuijian:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_main_fragment, new TuiJianFragment()).commit();
                break;
            case R.id.ll_duanzi:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_main_fragment, new DuanZiFragment()).commit();
                break;
            case R.id.ll_shipin:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_main_fragment, new ShiPinFragment()).commit();
                break;
            case R.id.iv_wode:
                menu.showMenu();
                break;
        }
    }
}
