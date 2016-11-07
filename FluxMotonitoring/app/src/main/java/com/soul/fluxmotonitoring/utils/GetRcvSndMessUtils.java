package com.soul.fluxmotonitoring.utils;

import android.content.Context;
import android.net.TrafficStats;
import android.text.format.Formatter;

import java.io.File;

/**
 * @author Administrator
 * @date 2015-7-29上午10:51:44
 * @公司 黑马程序员09期
 * @描述 获取接收和发送的流量信息
 *
 *　svn author：$Author: heima09 $
 *  svn version: $Rev: 141 $
 *  svn update time: $Date: 2015-07-29 11:08:34 +0800 (Wed, 29 Jul 2015) $
 */
public class GetRcvSndMessUtils {
	/**
	 * @param uid
	 *      app的uid
	 * @return
	 *      该app发送的流量信息
	 */
	public static String getFormatSnd(Context context,String uid){
		File file = new File("/proc/uid_stat/" + uid + "/tcp_snd");
		String res = "";
		try {
//			BufferedReader reader = new  BufferedReader(new InputStreamReader(new FileInputStream(file)));
//			String size = reader.readLine();
			res = Formatter.formatFileSize(context, Long.parseLong(String.valueOf(TrafficStats.getUidTxBytes(new Integer(uid)))));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	/**
	 * @param uid
	 *      app的uid
	 * @return
	 *      该app接收的流量信息
	 */
	public static String getFormatRec(Context context,String uid){
		File file = new File("/proc/uid_stat/" + uid + "/tcp_rcv");
		String res = "";
		try {
//			BufferedReader reader = new  BufferedReader(new InputStreamReader(new FileInputStream(file)));
//			String size = reader.readLine();
			res = Formatter.formatFileSize(context, Long.parseLong(String.valueOf(TrafficStats.getUidRxBytes(new Integer(uid)))));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
}
