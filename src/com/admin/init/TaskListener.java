package com.admin.init;

import javax.servlet.http.HttpServlet;

public class TaskListener extends HttpServlet{
	
	public void init(){
		System.out.println("����������ʼ��������");
		new InitTaskService().triggerTaskListener();
	}
}
