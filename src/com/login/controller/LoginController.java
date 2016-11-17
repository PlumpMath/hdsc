package com.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.index.model.Page;
import com.index.service.IndexService;
import com.login.model.User;
import com.login.service.LoginService;

public class LoginController extends HttpServlet{

	private LoginService loginService;
	
	public LoginController() {
		loginService = new LoginService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Page page = null;
		//已登录
		if(null!=request.getSession().getAttribute("user")){
			User user = (User) request.getSession().getAttribute("user");
			page = new Page();
			page.setPageNum(Integer.valueOf(request.getParameter("pageNum")));
			new IndexService().indexList(page,user.getId(),request);
			response.sendRedirect("pages/index.jsp");
		}else{ //未登录
			User user = new User();
			user.setUsername(request.getParameter("username"));
			user.setPassword(request.getParameter("password"));
			user.setId(loginService.findByUser(user));
			if(user.getId()!=0){
				request.getSession().setAttribute("user", user);
				new IndexService().indexList(page,user.getId(),request);
				response.sendRedirect("pages/index.jsp");
			}else{//错误信息提示问题待解决。。。
				request.setAttribute("loginError", "登录失败，用户名或密码错误");
				request.getRequestDispatcher("login.jsp").forward(request,response);
			}
		}
	}
}

