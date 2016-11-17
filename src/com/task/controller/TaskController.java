package com.task.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.task.service.TaskService;

public class TaskController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 TaskService taskService = new TaskService();
		 String result = taskService.deteleTask(request);
		 response.setCharacterEncoding("UTF-8");
		 response.setContentType("application/json;charset=UTF-8");
		 PrintWriter out = null;
		 try {
		     out = response.getWriter();
		     out.write(result);
		 } catch (IOException e) {
		     e.printStackTrace();
		 } finally {
		     if (out != null) {
		         out.close();
		     }
		 }
	}
}
