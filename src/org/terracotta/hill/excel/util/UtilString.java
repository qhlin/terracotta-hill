package org.terracotta.hill.excel.util;

import java.util.List;

public class UtilString {
	
	
	public static boolean isEmpty(String str){
		return str==null || str.trim().length() == 0;
	}
	public static boolean isNotEmpty(String str){
		return str!=null && str.trim().length() > 0;
	}

	/**
	 * 截取参数字符串[str],[length]个字节，生成新的字符串
	 * @param str 要截取的源字符串
	 * @param length 要截取的字符长度
	 * @return 截取后的字符串
	 */
	public static String subString(String str,int length){
		if(str == null){
			str = "";
		}else if(str.length()>length){
			str = str.substring(0,length);
		}
		return str;
	}
	
	
	public static String leftPadZero(String str,int length){
		StringBuffer sb = new StringBuffer();
		if(str==null){
			str="";
		}
		for(int i=0;i<length - str.length();i++){
			sb.append("0");
		}
		sb.append(str);
		return sb.toString();
	}
	
	/**
	 * 用于将数组中的字符串使用逗号串成字符串，<b>这里没加单引号</b>，适合生成sql的字段。
	 * @param strs
	 * @return
	 * @see UtilString#joinStringForSqlIn(String[])
	 */
	public static String joinStringByComma(String[] strs){
		StringBuffer sb = new StringBuffer();
		if(strs != null && strs.length>0){
			for(int i=0;i<strs.length;i++){
				sb.append(strs[i]);
				if(i !=strs.length-1){
					sb.append(",");
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * 用于将数组中的字符串使用逗号串成字符串，<b>有加单引号</b>，适合生成sql的in条件。
	 * @param strs
	 * @return
	 * @see UtilString#joinStringByComma(String[])
	 */
	
	public static String joinStringForSqlIn(String[] strs){
		StringBuffer sb = new StringBuffer();
		if(strs != null && strs.length>0){
			for(int i=0;i<strs.length;i++){
				sb.append("'"+strs[i]+"'");
				if(i !=strs.length-1){
					sb.append(",");
				}
			}
		}
		return sb.toString();
	}
	
	public static String joinStringForSqlIn(List strs){
		if(strs != null && strs.size()>0){
			return joinStringForSqlIn((String[])strs.toArray(new String[strs.size()]));
		}else{
			return "";
		}
	}
	
	/**
	 * 获取指定位数的随机码。
	 * 
	 * @return
	 */
	public static String generateRandomCode(int amount) {
		String[] keys = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
				"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c",
				"d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
				"x", "y", "z"};
		StringBuffer key = new StringBuffer();
		
		for(int i = 0; i < amount; i++) {
			int index = (int)(Math.random() * 62);
			key.append(keys[index]);
		}
		
		return key.toString();
	}
	
//	public static void main(String[] args){
//		
//		System.out.println(leftPadZero("a", 2));
//		System.out.println(leftPadZero("a", 1));
//		System.out.println(leftPadZero("a", 0));
//		System.out.println(leftPadZero(null, 2));
//	}
	

	
}
