package com.task.model;

import java.util.Date;

public class Task {
	private Integer id;
	private Integer userId;
	private String taskName;
	private Date endTime;
	private Integer bpfId;
	private Integer state;
	
	private String stateString;
	
	public String getStateString() {
		return stateString;
	}
	public void setStateString(String stateString) {
		this.stateString = stateString;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getBpfId() {
		return bpfId;
	}
	public void setBpfId(Integer bpfId) {
		this.bpfId = bpfId;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	
}
