package com.util;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpSession;
import com.po.Buser;
public class MyUtil {
	/**
	 * 获得时间字符串
	 */
	public static String getStringID(){
		String id=null;
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS"); 
		id=sdf.format(date);
		return id;
	}
	/**
	 * 防止文件重名
	 */
	public static String getRandom() {
		int n = (int)(Math.random()*100000000);
		return n + "";
	}
	/**
	 * 获得文件的扩展名
	 */
	public static String getFileExtendsName(String filenname) {
		int lastPointIndex = filenname.lastIndexOf(".");
		String extendsName = filenname.substring(lastPointIndex);
		return extendsName;
	}
	/**
	 * 获得用户ID
	 */
	public static Integer getUserId(HttpSession session) {
		Buser ruser = (Buser)session.getAttribute("emailuser");
		return ruser.getId();
	}
	/**
	 * 获得用户Email
	 */
	public static String getUserEmail(HttpSession session) {
		Buser ruser = (Buser)session.getAttribute("emailuser");
		return ruser.getBemail();
	}
	/**
	 * 下载保存时中文文件名字符编码转换方法
	 */
	public static String toUTF8String(String str){
		StringBuffer sb = new StringBuffer();
		int len = str.length();
		for(int i = 0; i < len; i++){
			//取出字符中的每个字符
			char c = str.charAt(i);
			//Unicode码值在0-255之间，不作处理
			if(c >= 0 && c <= 255){
				sb.append(c);
			}else{//转换UTF-8编码
				byte b[];
				try {
					b = Character.toString(c).getBytes("UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					b = null;
				}
				//转换为%HH的字符串形式
				for(int j = 0; j < b.length; j ++){
					int k = b[j];
					if(k < 0){
						k &= 255;
					}
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}

}
