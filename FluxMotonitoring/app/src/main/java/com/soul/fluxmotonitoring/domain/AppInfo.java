package com.soul.fluxmotonitoring.domain;

import android.graphics.drawable.Drawable;

/**
 * @author Administrator
 * @date 2015-7-23上午9:14:09
 * @公司 黑马程序员09期
 * @描述 app 的属性封装
 *
 *　svn author：$Author: heima09 $
 *  svn version: $Rev: 153 $
 *  svn update time: $Date: 2015-07-30 11:30:17 +0800 (Thu, 30 Jul 2015) $
 */
public class AppInfo {
	private boolean isSystem;//是否是系统app
	private String uid;
	public boolean isSystem() {
		return isSystem;
	}
	public void setSystem(boolean isSystem) {
		this.isSystem = isSystem;
	}
	private Drawable icon;//app的图标
	private String appName;// app的名字
	private String packName;//app的包名
	private long appSize;//app安装包的大小
	private boolean isRom;//是否安装下手机中
	private String appPath;//app安装目录
	public Drawable getIcon() {
		return icon;
	}
	public void setIcon(Drawable icon) {
		this.icon = icon;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getPackName() {
		return packName;
	}
	public void setPackName(String packName) {
		this.packName = packName;
	}
	public long getAppSize() {
		return appSize;
	}
	public void setAppSize(long appSize) {
		this.appSize = appSize;
	}
	public boolean isRom() {
		return isRom;
	}
	public void setRom(boolean isRom) {
		this.isRom = isRom;
	}
	public String getAppPath() {
		return appPath;
	}
	public void setAppPath(String appPath) {
		this.appPath = appPath;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	@Override
	public String toString() {
		return packName;
	}
	
	
}
