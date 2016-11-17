package com.index.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.index.service.PwdUpdateService;

/**
 * @Description:TODO
 * @author zhout
 * @date 2016年4月19日 下午8:51:12
*/
public class PwdUpdateController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    PwdUpdateService pwdUpdateService = new PwdUpdateService();
		String str = pwdUpdateService.updatePassword(request);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(str);
		if(null != out){
			out.close();
		}
	}
}
