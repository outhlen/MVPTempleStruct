package com.kelin.app.struct_pro.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.kelin.app.struct_pro.base.BaseLifecycleCallback;
import com.kelin.app.struct_pro.comm.Utils;

/**
 *  全局初始化
 *  初始化系统变量和常量（第三方库初始化、全局常量等）
 */
public class MyApp extends Application {

    private static MyApp instance;

    public static synchronized MyApp getInstance() {
        if (null == instance) {
            instance = new MyApp();
        }
        return instance;
    }

    public MyApp(){}

    /**
     * 这个最先执行
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
       // NetWorkManager.getInstance().init();
        /**
         *  生命周期管理初始化
         */
        Utils.init();
       // BaseLifecycleCallback.getInstance().init(this);
    }

    /**
     * 低内存的时候执行
     */
    @Override
    public void onLowMemory() {
        Log.d("Application", "onLowMemory");
        super.onLowMemory();
    }


    /**
     * HOME键退出应用程序
     * 程序在内存清理的时候执行
     */
    @Override
    public void onTrimMemory(int level) {
        Log.d("Application", "onTrimMemory");
        super.onTrimMemory(level);
    }

}
