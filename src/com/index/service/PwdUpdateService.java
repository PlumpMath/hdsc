package com.index.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.taglibs.standard.tag.el.sql.UpdateTag;

import com.alibaba.fastjson.JSON;
import com.javacommon.util.ConnectionUtil;
import com.javacommon.util.EncryptionUtil;
import com.javacommon.util.Msg;

/**
 * @Description:TODO
 * @author zhout
 * @date 2016年4月19日 下午8:52:52
*/
public class PwdUpdateService {
	
	private Connection connection;
	
	public PwdUpdateService(){
		connection = new ConnectionUtil().openConn();
	}
	
	public String updatePassword(HttpServletRequest request){
		String str = "UPDATE user u SET u.password = ? WHERE u.username=? AND u.password=?";
		PreparedStatement stmt = null;
		Msg msg = null;
		try{
			stmt = connection.prepareStatement(str);
			stmt.setString(1,EncryptionUtil.getSHA1(request.getParameter("newP")));
			stmt.setString(2,request.getParameter("user"));
			stmt.setString(3,EncryptionUtil.getSHA1(request.getParameter("oldP")));
			boolean result = false;
			int row = stmt.executeUpdate();
			if(row>0){
				result = true;
			}
		    msg = new Msg(result);
		} catch(SQLException e){
			e.printStackTrace();
		} finally{
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
		return JSON.toJSONString(msg);
	}
}
