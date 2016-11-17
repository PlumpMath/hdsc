package com.task.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.index.service.IndexService;
import com.javacommon.util.ConnectionUtil;
import com.javacommon.util.Msg;
import com.login.model.User;

public class TaskService {

	private Connection connection;

	public TaskService() {
		this.connection = new ConnectionUtil().openConn();
	}
	
	public String deteleTask(HttpServletRequest request){
		String sql = "DELETE FROM tasks WHERE id=?";
		PreparedStatement stmt = null;
		boolean flag = false;
		try {
			stmt = connection.prepareStatement(sql);
			int id = Integer.valueOf(request.getParameter("id"));
			stmt.setInt(1,id);
			stmt.execute();
			if(stmt.getUpdateCount()>0){
				flag = true;
			}
		} catch (Exception e) {
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
			updateIndexList(request);
		}
		Msg msg = new Msg(flag);
		String str = JSON.toJSONString(msg);
		return JSON.toJSONString(msg);
	}
	
	private void updateIndexList(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("user");
		new IndexService().indexList(null, user.getId(), request);
	}
}
