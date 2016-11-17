package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.model.User;

/**
 * @Description:防止未登录直接根据路径访问页面
 * @author zhout
 * @date 2016年4月2日 上午9:51:47
*/
public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		System.out.println("LoginFilter destroy...");
	}

	 
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
		User user = (User)httpServletRequest.getSession().getAttribute("user");
		if(null == user){
			httpServletResponse.sendRedirect("../login.jsp");
			System.out.println("登录验证失败");
		}else{//用户已登录
			chain.doFilter(httpServletRequest, httpServletResponse);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("LoginFilter init...");
	}
	
}
