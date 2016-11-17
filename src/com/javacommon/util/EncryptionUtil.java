package com.javacommon.util;

import java.security.MessageDigest;

/**
 * @Description:SHA1加密算法
 * @author zhout
 * @date 2016年4月20日 上午9:12:14
*/
public class EncryptionUtil {
	
	public static String getSHA1(String pwd){
		char[] hexDigits={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		
		try {
			MessageDigest mdtmp = MessageDigest.getInstance("SHA1");
			mdtmp.update(pwd.getBytes("utf-8"));
			
			byte[] md = mdtmp.digest();
			
			int j = md.length;
			
			char[] buf = new char[j * 2];
			int k = 0;
			
			for (int i = 0; i < j; i++) {
				byte bt = md[i];
				buf[k++] = hexDigits[bt >>> 4 & 0xf];
				buf[k++] = hexDigits[bt & 0xf];
			}
			return new String(buf);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pwd;
	}
	
	public static void main(String[] args) {
		System.out.println(EncryptionUtil.getSHA1("123456"));
	}
}
