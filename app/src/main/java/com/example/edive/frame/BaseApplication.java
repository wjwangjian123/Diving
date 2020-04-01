package com.example.edive.frame;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import cn.jpush.android.api.JPushInterface;


public class BaseApplication extends MultiDexApplication {
    public static BaseApplication baseApplication;
    public String Token = "";
    public String  userid = "" ;
    public String nickname = "";
    public String icon = "";
    public String Phone = "";
    public String Personalizedsignature = "";
    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication  = this;
        JPushInterface.setDebugMode(true); //允许被debug，正式版本的时候注掉
        JPushInterface.init(this);  //初始化
    }
    public static Context getAppContext(){
        return baseApplication.getApplicationContext();
    }

    public static BaseApplication getInstance() {
        return baseApplication;
    }
}
