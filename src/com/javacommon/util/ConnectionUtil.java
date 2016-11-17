package com.javacommon.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * @Description:数据库连接类
 * @author zhout
 * @date 2016年3月26日 下午11:18:09
 */
public class ConnectionUtil {
	
	// resourceLoc:数据库配置文件路径
	private final static String resourceLoc = "com/resources/db.properties";
	
	// connection:数据库连接对象
	private Connection connection;
	
	/**
	 * @Description: 打开数据库连接
	 * @param @return   
	 * @return Connection  
	 * @throws
	 * @author zhout 
	 * @date 2016年3月26日 下午11:18:31
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
	 * @Description: 关闭数据库连接
	 * @param @param conn   
	 * @return void  
	 * @throws
	 * @author zhout 
	 * @date 2016年3月26日 下午11:18:56
	 
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
