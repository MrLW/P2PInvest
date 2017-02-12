package com.lw.P2PInvest.common;

import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.lw.P2PInvest.utils.UiUtils;

/**
 * Created by lw on 2017/2/12.
 * 处理未捕获异常
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private CrashHandler() {
    }

    private static CrashHandler crashHandler;

    // 本来操作就是线程安全的。
    public static CrashHandler getInstance() {
        if (crashHandler == null) {
            crashHandler = new CrashHandler();
        }
        return crashHandler;
    }

    // 系统默认处理未捕获异常的处理器
    private Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;

    public void init() {
        defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 将当前类设置为处理未捕获异常的处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当系统出现未捕获的异常就会调用此方法
     *
     * @param t 线程
     * @param e 异常
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Log.e("TAG", "出现了未捕获异常：" + e.getMessage());
        new Thread() {
            @Override
            public void run() {
                super.run();
                Looper.prepare();
                Toast.makeText(UiUtils.getContext(), "出现了未捕获异常", Toast.LENGTH_SHORT).show();
                Looper.loop();

            }
        }.start();
        try {
            Thread.sleep(2000);
            //退出应用
            AppManager.getInstance().removeTop();
            //结束当前进程
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

    }
}
