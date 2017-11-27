package com.hxe.platform.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.yellowsmalltwo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 蒋順聪 on 2017/11/24.
 */

public class TuiJianFragment extends Fragment implements View.OnClickListener {

    private View view;
    private TableLayout tablayout_tuijian;
    private List<String> title;
    private List<Fragment> list;
    private LinearLayout ll_tuijian_remen;
    private LinearLayout ll_tuijian_guanzhu;
    private TextView tv_tuijian_tuijian;
    private View view_tuijian_tuijian;
    private TextView tv_tuijian_guanzhu;
    private View view_tuijian_guanzhu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(view == null){
            view = View.inflate(getContext(), R.layout.fragment_tuijian, null);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initview();
        initdata();

        //默认热门fragment
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmelayout_tuijian, new ReMenFragment()).commit();
        tv_tuijian_tuijian.setTextColor(Color.rgb(3,169,244));
        view_tuijian_tuijian.setVisibility(View.VISIBLE);

    }

    private void initview() {
        ll_tuijian_remen = view.findViewById(R.id.ll_tuijian_remen);
        ll_tuijian_guanzhu = view.findViewById(R.id.ll_tuijian_guanzhu);

        //推荐控件的点击事件
        tv_tuijian_tuijian = view.findViewById(R.id.tv_tuijian_tuijian);
        view_tuijian_tuijian = view.findViewById(R.id.view_tuijian_tuijian);

        //关注控件
        tv_tuijian_guanzhu = view.findViewById(R.id.tv_tuijian_guanzhu);
        view_tuijian_guanzhu = view.findViewById(R.id.view_tuijian_guanzhu);

        }

    private void initdata() {
        //推荐控件的点击事件
        ll_tuijian_remen.setOnClickListener(this);
        ll_tuijian_guanzhu.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_tuijian_remen:
                tv_tuijian_tuijian.setTextColor(Color.rgb(3,169,244));
                view_tuijian_tuijian.setVisibility(View.VISIBLE);
                tv_tuijian_guanzhu.setTextColor(Color.BLACK);
                view_tuijian_guanzhu.setVisibility(View.INVISIBLE);

                //替换fragme
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmelayout_tuijian, new ReMenFragment()).commit();

                break;

            case R.id.ll_tuijian_guanzhu:
                tv_tuijian_guanzhu.setTextColor(Color.rgb(3,169,244));
                view_tuijian_guanzhu.setVisibility(View.VISIBLE);
                tv_tuijian_tuijian.setTextColor(Color.BLACK);
                view_tuijian_tuijian.setVisibility(View.INVISIBLE);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmelayout_tuijian, new GuanZhuFragment()).commit();
                break;
        }
    }
}
