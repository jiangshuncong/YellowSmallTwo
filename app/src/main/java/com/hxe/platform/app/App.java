package com.hxe.platform.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.mob.MobApplication;

/**
 * Created by 蒋順聪 on 2017/11/20.
 */

public class App extends MobApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
