package com.lw.P2PInvest.common;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by lw on 2017/2/12.
 * 统一管理activity
 */
public class AppManager {

    private AppManager() {
    }

    private static AppManager appManager;

    private Stack<Activity> activityStack = new Stack<>();

    public static AppManager getInstance() {
        synchronized (AppManager.class) {
            if (appManager == null) {
                appManager = new AppManager();
            }
        }
        return appManager;
    }

    public void add(Activity activity) {
        if (activity != null) {
            activityStack.add(activity);
        }
    }

    public void remove(Activity activity) {
        if (activity != null) {
            for (int i = activityStack.size(); i >= 0; i--) {
                if (activityStack.get(i).getClass().equals(activity.getClass())) {
                    activityStack.get(i).finish();
                    activityStack.remove(i);
                }
            }
        }
    }

    public void removeTop() {
        Activity activity = activityStack.lastElement();
        activity.finish();
        activityStack.remove(activity);
    }

    public void removeAll() {
        for (int i = activityStack.size(); i >= 0; i--) {
            activityStack.get(i).finish();
            activityStack.remove(i);
        }
    }
}
