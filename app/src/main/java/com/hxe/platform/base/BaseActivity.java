package com.hxe.platform.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.yellowsmalltwo.R;

/**
 * Created by 蒋順聪 on 2017/11/19.
 */

public abstract class BaseActivity extends AppCompatActivity{

    public abstract boolean Immersion();
    public abstract int getLayoutId();
    public abstract void  init();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
        init();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(Immersion()) {
            if (hasFocus && Build.VERSION.SDK_INT >= 19) {
                View decorView = getWindow().getDecorView();
                decorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE//状态栏布局隐藏
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION//导航栏布局隐藏
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN//布局全屏
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION//导航栏隐藏
                                | View.SYSTEM_UI_FLAG_FULLSCREEN//全屏
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY//可下拉展示状态栏
                );
            }
        }
    }

    //无传参构造方法
    public void IntentStart(Class<?> myclass,Boolean flag){
        Intent intent = new Intent(getApplicationContext(),myclass);
        startActivity(intent);
        if(flag){
            finish();
        }
    }
    //有传参构造方法
    public void IntentStart(Class<?> myclass,Bundle bundle,Boolean flag){
        Intent intent = new Intent(getApplicationContext(),myclass);
        if(bundle != null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void ToastStart(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
        return;
    }
}
