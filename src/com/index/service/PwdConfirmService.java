package com.index.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.javacommon.util.ConnectionUtil;
import com.javacommon.util.EncryptionUtil;

public class PwdConfirmService {
	
	private Connection connection;
	
	public PwdConfirmService() {
		connection = new ConnectionUtil().openConn();
	}
	
	public boolean confirmPwd(HttpServletRequest request){
		boolean isValid = false;
		String str = "SELECT * FROM user u WHERE u.username=? AND u.password=?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = connection.prepareStatement(str);
			String user = request.getParameter("user");
			String pwd = request.getParameter("originalPwd");
			stmt.setString(1,user);
			stmt.setString(2,EncryptionUtil.getSHA1(pwd));
			rs = stmt.executeQuery();
			while(rs.next()){
				isValid = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(null != rs){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(null != stmt){
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(null != connection){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return isValid;
	}
}
