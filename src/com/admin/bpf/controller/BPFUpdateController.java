package com.admin.bpf.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.bpf.service.BPFUpdateService;

/**
 * @Description:TODO
 * @author zhout
 * @date 2016年4月22日 下午2:53:10
*/
public class BPFUpdateController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 BPFUpdateService defaultBPFService = new BPFUpdateService();
		 String msg = defaultBPFService.saveDefaultRule(request);
		 response.setCharacterEncoding("UTF-8");
		 response.setContentType("application/json;charset=UTF-8");
		 PrintWriter out = response.getWriter();
		 out.print(msg);
		 if(null != out){
			 out.close();
		 }
	}
}
