package com.admin.bpf.service;

import java.sql.Connection;
import java.sql.PreparedStatement;


import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.javacommon.util.ConnectionUtil;
import com.javacommon.util.Msg;

/**
 * @Description:TODO
 * @author zhout
 * @date 2016年4月22日 下午2:56:53
*/
public class BPFUpdateService {
	
	private Connection connection;
	
	public BPFUpdateService(){
		connection = new ConnectionUtil().openConn();
	}
	
	public String saveDefaultRule(HttpServletRequest request){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean result = false;
		boolean isEmpty = true;
		String bpfStr = request.getParameter("bpf");		
		try {
			String query = "SELECT COUNT(*) total FROM bpf";
			stmt = connection.prepareStatement(query);
			rs = stmt.executeQuery();
			while(rs.next()){
				if(rs.getInt("total")>0){
					isEmpty = false;
				}
			}
			if(!isEmpty){
				result = updateBPF(bpfStr);
			}else{
				result = insertBPF(bpfStr);
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			if(null != rs){
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(null != stmt){
				try {
					stmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return JSON.toJSONString(new Msg(result));
	}
	
	private boolean updateBPF(String bpf){
		PreparedStatement stmt = null;
		String updateStr = "UPDATE bpf set bpf=? WHERE id=1";
		try {
			stmt = connection.prepareStatement(updateStr);
			stmt.setString(1,bpf);
			if(stmt.executeUpdate()>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(null != stmt){
				try {
					stmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(null != connection){
				try {
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	private boolean insertBPF(String bpf){
		PreparedStatement stmt = null;
		String insertStr = "INSERT INTO bpf(bpf) VALUES (?)";
		try {
			stmt = connection.prepareStatement(insertStr);
			stmt.setString(1,bpf);
		    if(stmt.executeUpdate()>0){
		    	return true;
		    }
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(null != stmt){
				try {
					stmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(null != connection){
				try {
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
}
