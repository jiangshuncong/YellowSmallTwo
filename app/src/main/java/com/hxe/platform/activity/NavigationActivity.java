package com.hxe.platform.activity;

import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.yellowsmalltwo.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hxe.platform.base.BaseActivity;

public class NavigationActivity extends BaseActivity implements View.OnClickListener {

    private ViewPager navigation_viewpager;
    private int icon[] = {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.shanping};
    private RelativeLayout rv_layout;
    private View inflate;

    private void initview() {

        navigation_viewpager = findViewById(R.id.navigation_viewpager);
        rv_layout = findViewById(R.id.rv_layout);

    }

    @Override
    public void onClick(View view) {

        IntentStart(LoginActivity.class,true);

    }

    @Override
    public boolean Immersion() {
        return true;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_navigation;
    }

    @Override
    public void init() {
        initview();

        navigation_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 3){
                    rv_layout.setVisibility(View.VISIBLE);
                }else{
                    rv_layout.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        navigation_viewpager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return icon.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view  == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {

                if(position == 3){
                    inflate = View.inflate(NavigationActivity.this, R.layout.navigation_item2, null);
                }else{
                    inflate = View.inflate(NavigationActivity.this, R.layout.navigation_item, null);
                }

                SimpleDraweeView iv = inflate.findViewById(R.id.iv_navigation);

               /* Glide.with(NavigationActivity.this).load(icon[position])
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(new GlideDrawableImageViewTarget(iv,1));*/

                DraweeController draweeController  = Fresco.newDraweeControllerBuilder()
                        .setUri(Uri.parse("res://" + "drawable"+"/"+icon[position]))
                        .setAutoPlayAnimations(true)
                        .build();

                iv.setController(draweeController);

                container.addView(inflate);

                return inflate;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                // super.destroyItem(container, position, object);
                container.removeView((View) object);
            }
        });

        //点击事件
        rv_layout.setOnClickListener(this);
    }
}
