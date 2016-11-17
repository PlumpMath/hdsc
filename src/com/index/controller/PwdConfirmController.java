package com.index.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.index.service.PwdConfirmService;
import com.javacommon.util.Msg;

public class PwdConfirmController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 PwdConfirmService pwdConfirmService = new PwdConfirmService();
		 String user = request.getParameter("user");
		 String pwd = request.getParameter("originalPwd");
		 boolean isValid = false;
		 isValid = pwdConfirmService.confirmPwd(request);
		 response.setCharacterEncoding("UTF-8");
		 response.setContentType("application/json;charset=UTF-8");
		 Msg msg = new Msg(isValid);
		 String result = JSON.toJSONString(msg);
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
