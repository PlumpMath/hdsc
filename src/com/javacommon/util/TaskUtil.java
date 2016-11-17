package com.javacommon.util;

import java.util.List;

import com.task.model.Task;

/**
 * @Description:TODO
 * @author zhout
 * @date 2016年4月16日 上午10:53:15
*/
public class TaskUtil {
	
	public static int getCompleteNum(List<Task> task){
		int isComplete = 0;
		for(Task t:task){
			if(t.getState()==1){
				isComplete++;
			}
		}
		return isComplete;
	}
}
