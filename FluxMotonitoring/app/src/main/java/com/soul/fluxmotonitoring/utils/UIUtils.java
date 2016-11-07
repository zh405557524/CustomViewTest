package com.soul.fluxmotonitoring.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;

import com.bdxht.communication.BaseApplication;

import java.util.List;

/**
 * @author soul
 * @作者：祝明
 * @描述： 和Ui相关的一些静态工具的方法
 * @创建时间：2015-8-15 下午8:51:21
 */
public class UIUtils {
    /**
     * 得到上下文
     */
    public static Context getContext() {
        return BaseApplication.getmContext();
    }


    /**
     * 得到主线程的handler
     */
    public static Handler getHandler() {

        return BaseApplication.getmHandler();
    }

    /**
     * 得到resouce对象
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 得到string.xml中的一个字符串
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /**
     * 得到string.xml中的一个字符串数组
     */
    public static String[] getStringArr(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 得到color.xml中的颜色值
     */
    public static int getColor(int colorId) {
        return getResources().getColor(colorId);
    }

    /**
     * 得到应用程序的包名
     */
    public static String getPackageName() {
        return getContext().getPackageName();
    }

    /**
     * 得到主线程id
     */
    public static long getMainThreadId() {
        return BaseApplication.getmMainThreadId();
    }

    /**
     * 得到一个主线程的handler
     */
    public static Handler getMainThreadHandler() {
        return BaseApplication.getmHandler();
    }

    /**
     * 安全的执行一个task
     */
    public static void postTaskSafely(Runnable task) {
        int curThreadId = android.os.Process.myTid();
        long mainThreadId = getMainThreadId();
        // 如果当前线程是主线程
        if (curThreadId == mainThreadId) {
            task.run();
        } else {
            // 如果当前线程不是主线程
            getMainThreadHandler().post(task);
        }
    }

    /**
     * 添加actvity
     *
     * @param activity
     */
    public static void addActivity(Activity activity) {
        BaseApplication.addActivity(activity);
    }

    /**
     * 获取actvity
     *
     * @return
     */
    public static List<Activity> getActivites() {
        return BaseApplication.activities;
    }
}
