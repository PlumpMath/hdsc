package com.task.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.task.service.TaskAddService;

public class TaskAddController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 TaskAddService taskAddService = new TaskAddService();
		 String result = taskAddService.addTask(request);
		 response.setContentType("application/json;charset=UTF-8");
		 response.setCharacterEncoding("UTF-8");
		 PrintWriter writer = null;
		 try {
			writer = response.getWriter();
			writer.write(result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(null != writer){
				writer.close();
			}
		}
	}
}
