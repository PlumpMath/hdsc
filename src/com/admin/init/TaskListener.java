package com.admin.init;

import javax.servlet.http.HttpServlet;

public class TaskListener extends HttpServlet{
	
	public void init(){
		System.out.println("¼àÌıÆ÷±»³õÊ¼»¯¡£¡£¡£");
		new InitTaskService().triggerTaskListener();
	}
}
