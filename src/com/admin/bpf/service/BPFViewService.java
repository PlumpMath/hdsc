package com.admin.bpf.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.admin.bpf.model.BPF;
import com.alibaba.fastjson.JSON;
import com.javacommon.util.ConnectionUtil;

/**
 * @Description:TODO
 * @author zhout
 * @date 2016年4月22日 下午3:45:19
*/
public class BPFViewService {

	private Connection connection;
	
	public BPFViewService(){
		connection = new ConnectionUtil().openConn();
	}
	
	public String viewDefaultBPF(){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		BPF bpfModel = null;
		try {
			String sql = "SELECT b.bpf rule FROM bpf b";
			stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery();
		    bpfModel = new BPF();
			while(rs.next()){
				bpfModel.setBpf(rs.getString("rule"));
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
		return JSON.toJSONString(bpfModel);
	}
}
