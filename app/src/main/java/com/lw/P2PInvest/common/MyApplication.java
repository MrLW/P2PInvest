package com.lw.P2PInvest.common;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by lw on 2017/2/12.
 */

public class MyApplication extends Application {
    public static Context context;
    public static Handler handler;
    public static Thread mainThread;
    public static long mainThreadId;


    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
        handler = new Handler();
        mainThread = Thread.currentThread();
        mainThreadId = android.os.Process.myPid();

        CrashHandler.getInstance().init();
    }
}
