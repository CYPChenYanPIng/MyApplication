package com.example.uxin.myapplication;

import android.app.Application;
import android.content.Context;

/**
 * @author chenyanping
 * @date 2020-05-12
 */
public class ChenApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = ChenApplication.this;
    }

    public static Context getContext(){
        return mContext;
    }
}
