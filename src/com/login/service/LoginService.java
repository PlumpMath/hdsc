package com.login.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javacommon.util.ConnectionUtil;
import com.javacommon.util.EncryptionUtil;
import com.login.model.User;

public class LoginService{

	private Connection connection;
	
	public int findByUser(User user){ 
		int result = 0;
		String sql = "select u.id id from user u where u.username=? and u.password=?";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			connection = new ConnectionUtil().openConn();
			pstm = connection.prepareStatement(sql);
			pstm.setString(1,user.getUsername());
			pstm.setString(2,EncryptionUtil.getSHA1(user.getPassword()));
			rs = pstm.executeQuery();
			if(rs.next()){
				result = rs.getInt("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(null != rs){
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(null != pstm){
				try {
					pstm.close();
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
		return result;
	}
}
