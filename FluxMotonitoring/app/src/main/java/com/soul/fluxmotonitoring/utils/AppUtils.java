package com.soul.fluxmotonitoring.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.itheima09.mobilegurad.domain.AppInfo;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Environment;

/**
 * @author Administrator
 * @date 2015-7-23上午9:05:25
 * @公司 黑马程序员09期
 * @描述 软件管家的工具类
 *
 *　svn author：$Author: heima09 $
 *  svn version: $Rev: 140 $
 *  svn update time: $Date: 2015-07-29 10:42:24 +0800 (Wed, 29 Jul 2015) $
 */
public class AppUtils {
	//1. 获取rom的剩余空间
	/**
	 * @return
	 *     rom的剩余空间 单位byte
	 */
	public static long getAvailRom(){
		long size = 0;
		File dataDirectory = Environment.getDataDirectory();
		//byte
		size = dataDirectory.getFreeSpace();
		
		return size;
	}
	
	//2. 获取sd卡的剩余空
	/**
	 * @return
	 *     sd的剩余空间 单位byte
	 */
	public static long getAvailSD(){
		long size = 0;
		File dataDirectory = Environment.getExternalStorageDirectory();
		//byte
		size = dataDirectory.getFreeSpace();
		
		return size;
	}
	
	
	//3. 所有安装的app信息
	/**
	 * @param context
	 * @return
	 *     手机中所有安装的app信息
	 */
	public static List<AppInfo> getAllApps(Context context){
		List<AppInfo> appInfos = new ArrayList<AppInfo>();
		//获取所有安装的app 
		PackageManager pm = context.getPackageManager();
		
		List<PackageInfo> packages = pm.getInstalledPackages(0);
		//循环取数据，封装数据
		AppInfo appInfo;
		for (PackageInfo packageInfo : packages) {
			//信息的封装
			appInfo = new AppInfo();
			
			//封装包名
			appInfo.setPackName(packageInfo.packageName);
			//app的图标
			Drawable icon = packageInfo.applicationInfo.loadIcon(pm);
			appInfo.setIcon(icon);
			
			//app的名字
			CharSequence appName = packageInfo.applicationInfo.loadLabel(pm);
			appInfo.setAppName(appName + "");
			
			//app的安装目录
			String appPath = packageInfo.applicationInfo.sourceDir;
			appInfo.setAppPath(appPath);
			
			//app的大小 单位是byte
			File file = new File(appPath);
			appInfo.setAppSize(file.length());
			
			//安装位置
			if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) != 0){
				appInfo.setRom(false);//sd卡
			} else {
				appInfo.setRom(true);//手机中
			}
			
			//app的类型
			if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0){
				appInfo.setSystem(true);//系统app
			} else {
				appInfo.setSystem(false);//用户app
			}
			
			//uid
			appInfo.setUid(packageInfo.applicationInfo.uid + "");
			
			//添加对象
			appInfos.add(appInfo);
		}
		return appInfos;
	}
	
	
}
