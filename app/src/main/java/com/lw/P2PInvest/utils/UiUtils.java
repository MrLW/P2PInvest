package com.lw.P2PInvest.utils;

import android.content.Context;
import android.os.Handler;

import com.lw.P2PInvest.common.MyApplication;

/**
 * Created by lw on 2017/2/12.
 */

public class UiUtils {

    public static Context getContext() {
        return MyApplication.context;
    }

    public static Handler getHandler() {
        return MyApplication.handler;
    }


}
