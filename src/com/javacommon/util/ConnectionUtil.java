package com.javacommon.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * @Description:���ݿ�������
 * @author zhout
 * @date 2016��3��26�� ����11:18:09
 */
public class ConnectionUtil {
	
	// resourceLoc:���ݿ������ļ�·��
	private final static String resourceLoc = "com/resources/db.properties";
	
	// connection:���ݿ����Ӷ���
	private Connection connection;
	
	/**
	 * @Description: �����ݿ�����
	 * @param @return   
	 * @return Connection  
	 * @throws
	 * @author zhout 
	 * @date 2016��3��26�� ����11:18:31
	 */
	public Connection openConn(){
		Properties properties = new Properties();
		try {
			properties.load(this.getClass().getClassLoader().getResourceAsStream(
					resourceLoc));
			String driver = properties.getProperty("Driver");
			String url = properties.getProperty("URL");
			String username = properties.getProperty("user");
			String password = properties.getProperty("pwd");
			Class.forName(driver);
			return this.connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	 
	/**
	 * @Description: �ر����ݿ�����
	 * @param @param conn   
	 * @return void  
	 * @throws
	 * @author zhout 
	 * @date 2016��3��26�� ����11:18:56
	 
	public void closeConn(){
		try {
			if(null != connection){
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	*/
}
