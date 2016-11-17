package com.index.model;

import java.util.List;

import com.task.model.Task;

/**
 * @Description:TODO
 * @author zhout
 * @date 2016年4月15日 下午5:01:28
*/
public class IndexDao {
	
	private int totalTask;
	private int task_ing;
	private int task_done;
	private List<Task> tasks;
	private List<Integer> page;
	
	public int getTotalTask() {
		return totalTask;
	}
	public void setTotalTask(int totalTask) {
		this.totalTask = totalTask;
	}
	public int getTask_ing() {
		return task_ing;
	}
	public void setTask_ing(int task_ing) {
		this.task_ing = task_ing;
	}
	public int getTask_done() {
		return task_done;
	}
	public void setTask_done(int task_done) {
		this.task_done = task_done;
	}
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	public List<Integer> getPage() {
		return page;
	}
	public void setPage(List<Integer> page) {
		this.page = page;
	}
}
