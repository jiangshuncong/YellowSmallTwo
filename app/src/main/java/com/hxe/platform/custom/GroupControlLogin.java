package com.hxe.platform.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.yellowsmalltwo.R;

/**
 * Created by 蒋順聪 on 2017/11/20.
 */

public class GroupControlLogin extends RelativeLayout {

    public GroupControlLogin(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.login_combinaction_control,this);
    }

    public GroupControlLogin(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.login_combinaction_control,this);
    }

    public GroupControlLogin(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.login_combinaction_control,this);
    }



}
