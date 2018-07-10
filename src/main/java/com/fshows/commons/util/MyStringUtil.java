package com.fshows.commons.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyStringUtil {

	public  static  final  String EMPTY = "";

	public static boolean isNullStr(String str) {
		if (null == str || str.length() == 0) {
			return true;
		}
		str = str.trim().toLowerCase();
		if ("null".equalsIgnoreCase(str)) {
			return true;
		}
		return false;
	}
	
	public static boolean isNotNull(String str) {
		return !isNullStr(str);
	}

	public  static String trim(String str){
		if(isNullStr(str)){
			return EMPTY;
		}
		return replaceBlank(str);
	}

	//去掉空格 回车 \t
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		dest = dest.replace("'", "");
		return dest;
	}
	
	public static void main(String[] args){
	   	String str = "    66  a 232 lj	";
	   	System.out.println(trim(str));
	}

}
