package com.example.jpushdemo;

import android.app.Application;
import android.content.Context;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.JThirdPlatFormInterface;

/**
 * For developer startup JPush SDK
 * 
 * 一般建议在自定义 Application 类里初始化。也可以在主 Activity 里。
 */
public class ExampleApplication extends Application {
    private static final String TAG = "JIGUANG-Example";

    @Override
    public void onCreate() {    	     
    	 Logger.d(TAG, "[ExampleApplication] onCreate");
         super.onCreate();

         JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
         JPushInterface.init(this);

         JThirdPlatFormInterface jThirdPlatFormInterface = new JThirdPlatFormInterface() {
             @Override
             public void init(Context context) {

             }

             @Override
             public String getToken(Context context) {
                 return null;
             }

             @Override
             public boolean isSupport(Context context) {
                 return false;
             }

             @Override
             public byte getRomType(Context context) {
                 return 0;
             }

             @Override
             public void register(Context context) {

             }

             @Override
             public String getRomName() {
                 return null;
             }

             @Override
             public String getAppkey(Context context) {
                 return null;
             }

             @Override
             public String getAppId(Context context) {
                 return null;
             }

             @Override
             public boolean isNeedClearToken(Context context) {
                 return false;
             }
         };
         // 初始化 JPush
    }
}
