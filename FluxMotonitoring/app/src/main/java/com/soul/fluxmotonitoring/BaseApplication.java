package com.soul.fluxmotonitoring;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Process;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * * @author Administrator
 *
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2015/12/14 20:34
 */
public class BaseApplication extends Application {

    private static Context mContext;
    private static Handler mHandler;
    private static long mMainThreadId;
    private static Thread mMainThread;
    public static Map<String, Long> map;

    public static Context getmContext() {
        return mContext;
    }

    public static Handler getmHandler() {
        return mHandler;
    }

    public static long getmMainThreadId() {
        return mMainThreadId;
    }

    public static Thread getmMainThread() {
        return mMainThread;
    }

    public static List<Activity> activities = new ArrayList<Activity>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }


    @Override
    public void onCreate() {
        // 创建一些常见的变量
        // 1 上下文

        mContext = getApplicationContext();
        // 2 创建一个handler
        mHandler = new Handler() {

        };
        // 3 的到一个主线程 id
        mMainThreadId = Process.myTid();
        // 4 得到主线程
        mMainThread = Thread.currentThread();
        //                registerException();

        int pid = Process.myPid();
        String processAppName = getAppName(pid);
        // 如果app启用了远程的service，此application:onCreate会被调用2次
        // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
        // 默认的app会在以包名为默认的process name下运行，如果查到的process name不是app的process name就立即返回

        if (processAppName == null || !processAppName.equalsIgnoreCase(mContext.getPackageName())) {
            //"com.easemob.chatuidemo"为demo的包名，换到自己项目中要改成自己包名

            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }

        super.onCreate();
    }



    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }

    private void registerException() {
        Thread.currentThread().setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

            @Override
            public void uncaughtException(Thread thread, Throwable ex) {

                //获取手机的配置信息
                //动态获取手机配置
                StringWriter sw = new StringWriter();
                Class type = Build.class;
                Field[] declaredFields = type.getDeclaredFields();
                for (Field field : declaredFields) {
                    try {
                        sw.append(field.getName() + ":" + field.get(null) + "\n");
                    } catch (IllegalAccessException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                PrintWriter pw = new PrintWriter(sw);
                // 异常的捕获
                System.out.println(ex);
                //记录异常信息
                //写到文件中,sd卡
                File file = new File(Environment.getExternalStorageDirectory(), "excep.txt");

                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    PrintWriter out = new PrintWriter(pw);
                    ex.printStackTrace(out);
                    // pw 写到  fos中
                    fos.write(sw.toString().getBytes());
                    out.close();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                //打开自己的主界面
                //程序已经要挂了
            }
        });
    }
}
