package com.javacommon.util;

import java.sql.Connection;

/**
 * @Description:TODO
 * @author zhout
 * @date 2016��4��20�� ����9:37:56
*/
public class LogUtil {

	private static Connection connection;
	
	static{
		connection = new ConnectionUtil().openConn();
	}
	
	public static void addLog(String str){
		
	}
}
