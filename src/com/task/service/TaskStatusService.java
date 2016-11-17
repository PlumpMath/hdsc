package com.task.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.javacommon.util.ConnectionUtil;
import com.task.model.TaskStatus;

public class TaskStatusService {

	private Connection connection;
	
	public TaskStatusService(){
		connection = new ConnectionUtil().openConn();
	}
	
	public String seeTaskStatus(HttpServletRequest request){
		int taskId = Integer.valueOf(request.getParameter("taskId"));
		String sql = "SELECT packet_numbers packetNumbers,tcp_numbers tcpNumbers,udp_numbers udpNumbers,"+
		             "icmp_numbers icmpNumbers,arp_numbers arpNumbers,traffic_statistics trafficStatistics,"+
				     "packet_size packetSize,bpf bpf ,error error FROM packet_message WHERE task_id = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		TaskStatus taskStatus = null;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1,taskId);
			rs = stmt.executeQuery();
			taskStatus = new TaskStatus();
			while(rs.next()){
				taskStatus.setPacketNumbers(rs.getInt("packetNumbers"));
				taskStatus.setTcpNumbers(rs.getInt("tcpNumbers"));
				taskStatus.setUdpNumbers(rs.getInt("udpNumbers"));
				taskStatus.setIcmpNumbers(rs.getInt("icmpNumbers"));
				taskStatus.setArpNumbers(rs.getInt("arpNumbers"));
				taskStatus.setPacketSize(rs.getInt("packetSize"));
				taskStatus.setTrafficStatistics(rs.getInt("trafficStatistics"));
				taskStatus.setBpf(rs.getString("bpf"));
			    taskStatus.setError(rs.getInt("error"));
			    taskStatus.setDataArray();
			}
		} catch (Exception e) {
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
		String string = JSON.toJSONString(taskStatus);
		return JSON.toJSONString(taskStatus);
	}
}
