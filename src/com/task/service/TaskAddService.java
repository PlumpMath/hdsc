package com.task.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.index.service.IndexService;
import com.javacommon.util.ConnectionUtil;
import com.javacommon.util.Msg;
import com.login.model.User;

public class TaskAddService {
	
	private Connection connection;
	
	public TaskAddService(){
		this.connection = new ConnectionUtil().openConn();
	}
	
	public String addTask(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("user");
		int userId = user.getId();
		String sql = "INSERT INTO tasks(user_id,task_name,bpf,state) VALUES(?,?,?,?)";
		PreparedStatement stmt = null;
		int result = 0;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, userId);
			stmt.setString(2, request.getParameter("taskname"));
			stmt.setString(3, request.getParameter("bpf"));
			stmt.setInt(4, 0); 
			result = stmt.executeUpdate();
		} catch (SQLException e) {
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
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		updateIndexList(request);
		return JSON.toJSONString(new Msg(result));
	}

	//更新任务数
	private void updateIndexList(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("user");
		new IndexService().indexList(null, user.getId(), request);
	}
}
