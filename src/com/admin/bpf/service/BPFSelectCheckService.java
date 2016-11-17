package com.admin.bpf.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.enterprise.inject.New;
import javax.servlet.http.HttpServletRequest;

import com.admin.bpf.model.BPF;
import com.alibaba.fastjson.JSON;
import com.javacommon.util.ConnectionUtil;
import com.javacommon.util.Msg;

public class BPFSelectCheckService {

	private Connection connection;
	
	public BPFSelectCheckService(){
		this.connection = new ConnectionUtil().openConn();
	}
	
	public String loadSelectList(){
		String sql = "SELECT b.id id FROM bpf b";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int bpfId = 0;
		try {
			stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				bpfId = rs.getInt("id");
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
			if(null != stmt){
				try {
					stmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return JSON.toJSONString(new Msg(bpfId));
	}
}
