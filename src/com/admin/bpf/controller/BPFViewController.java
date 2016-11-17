package com.admin.bpf.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.bpf.service.BPFViewService;

/**
 * @Description:TODO
 * @author zhout
 * @date 2016年4月22日 下午3:43:38
*/
public class BPFViewController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 BPFViewService bpfViewService = new BPFViewService();
		 String data = bpfViewService.viewDefaultBPF();
		 response.setCharacterEncoding("UTF-8");
		 response.setContentType("application/json;charset=UTF-8");
		 PrintWriter out = response.getWriter();
		 out.print(data);
		 if(null != out){
			 out.close();
		 }
	}
}
